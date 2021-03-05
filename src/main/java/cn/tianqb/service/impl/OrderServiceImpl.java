package cn.tianqb.service.impl;

import cn.tianqb.common.Assert;
import cn.tianqb.common.PageBean;
import cn.tianqb.enums.OrderEvent;
import cn.tianqb.enums.OrderStatusEnum;
import cn.tianqb.enums.StatusEnum;
import cn.tianqb.exception.AppException;
import cn.tianqb.mapper.OrderDetailMapper;
import cn.tianqb.mapper.OrderMapper;
import cn.tianqb.pojo.example.OrderExample;
import cn.tianqb.pojo.po.Order;
import cn.tianqb.pojo.po.OrderDetailPO;
import cn.tianqb.pojo.query.OrderQuery;
import cn.tianqb.pojo.vo.CommentVO;
import cn.tianqb.service.OrderService;
import cn.tianqb.utils.UUIDUtils;
import cn.tianqb.utils.WebHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/5 19:48
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Boolean create(Order order) {
        orderCheck(order);
        orderInit(order);
        orderDetailCalculate(order);
        return Boolean.TRUE;
    }

    private void orderCheck(Order order) {
        Assert.isNull(order, "order is null");
        Assert.isTrue(CollectionUtils.isEmpty(order.getOrderDetails()), "order is empty");
        Assert.isNull(order.getPhone(), "phone is null");
    }

    private void orderInit(Order order) {
        order.setCreator(WebHelper.getUsername());
        order.setModifier(WebHelper.getUsername());
        order.setOrderId(UUIDUtils.uuid());
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
    }

    /**
     * 计算订单详情内容
     */
    private void orderDetailCalculate(Order order) {
        List<OrderDetailPO> orderDetails = order.getOrderDetails();
        orderDetails.forEach(detail -> {
            detail.setCreator(WebHelper.getUsername());
            detail.setModifier(WebHelper.getUsername());
            detail.setOrderId(order.getOrderId());
            detail.setStatus(StatusEnum.NORMAL.getCode());
            detail.setTotalPrice(detail.getDishesPrice().multiply(new BigDecimal(detail.getTotal())));
            BigDecimal totalPrice = order.getTotalPrice();
            if (ObjectUtils.isEmpty(totalPrice)) {
                totalPrice = new BigDecimal(0);
            }
            order.setTotalPrice(totalPrice.add(detail.getTotalPrice()));
            orderDetailMapper.insertSelective(detail);
        });
    }

    @Override
    public PageInfo<Order> findList(OrderQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(query.getOrderId())) {
            criteria.andOrderIdEqualTo(query.getOrderId());
        }
        if (!ObjectUtils.isEmpty(query.getStatus())) {
            criteria.andStatusEqualTo(query.getStatus());
        }
        List<Order> list = orderMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public Boolean pay(String orderId) {
        PageHelper.startPage(1, 1);
        OrderExample example = new OrderExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<Order> orders = orderMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(orders)) {
            Order order = orders.get(0);
            orderStatusCheck(OrderEvent.PAYING, OrderStatusEnum.fromCode(order.getStatus()));
            order.setStatus(OrderStatusEnum.PAID.getCode());
            return orderMapper.updateByPrimaryKey(order) == 1;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean comment(CommentVO commentVO) {
        return null;
    }

    @Override
    public Order findOne(OrderQuery query) {
        Assert.isNull(query, "query is null");
        Assert.isNull(query.getOrderId(), "orderId is empty");
        PageHelper.startPage(1, 1);
        OrderExample example = new OrderExample();
        example.createCriteria().andOrderIdEqualTo(query.getOrderId());
        List<Order> orders = orderMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(orders)) {
            return orders.get(0);
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        Assert.isNull(id, "id is null");
        Order order = orderMapper.selectByPrimaryKey(id);
        orderStatusCheck(OrderEvent.DELETE, OrderStatusEnum.fromCode(order.getStatus()));
        order.setStatus(OrderStatusEnum.DELETED.getCode());
        return orderMapper.updateByPrimaryKey(order) == 1;
    }

    @Override
    public Map statistical() {
        return null;
    }

    /**
     * 状态检查
     * @param orderEvent 订单动作
     * @param orderStatus 目前订单的状态
     */
    private void orderStatusCheck(OrderEvent orderEvent, OrderStatusEnum orderStatus) {
        if (ObjectUtils.isEmpty(orderStatus)) {
            throw new AppException("当前订单异常", HttpStatus.FORBIDDEN.value());
        }
        switch (orderEvent) {
            case PAYING: {
                if (!orderStatus.equals(OrderStatusEnum.NOT_PAY)) {
                    throw new AppException("当前订单无法支付", HttpStatus.FORBIDDEN.value());
                }
                break;
            }
            case COMMENT: {
                if (!orderStatus.equals(OrderStatusEnum.DONE)) {
                    throw new AppException("当前订单未完成，无法评价", HttpStatus.FORBIDDEN.value());
                }
                break;
            }
            case DELETE: {
                break;
            }
            default: {
                throw new AppException("不支持当前操作", HttpStatus.FORBIDDEN.value());
            }
        }
    }
}
