package cn.tianqb.service.impl;

import cn.tianqb.common.Assert;
import cn.tianqb.enums.OrderEvent;
import cn.tianqb.enums.OrderStatusEnum;
import cn.tianqb.enums.StatusEnum;
import cn.tianqb.exception.AppException;
import cn.tianqb.mapper.OrderDetailMapper;
import cn.tianqb.mapper.OrderMapper;
import cn.tianqb.pojo.example.OrderExample;
import cn.tianqb.pojo.po.OrderPO;
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
    public Boolean create(OrderPO orderPO) {
        orderCheck(orderPO);
        orderInit(orderPO);
        orderDetailInit(orderPO);
        return Boolean.TRUE;
    }

    private void orderCheck(OrderPO orderPO) {
        Assert.isNull(orderPO, "orderPO is null");
        Assert.isTrue(CollectionUtils.isEmpty(orderPO.getOrderDetails()), "orderPO is empty");
        Assert.isNull(orderPO.getPhone(), "phone is null");
    }

    private void orderInit(OrderPO orderPO) {
        orderPO.setCreator(WebHelper.getUsername());
        orderPO.setModifier(WebHelper.getUsername());
        orderPO.setOrderId(UUIDUtils.uuid());
        orderPO.setStatus(OrderStatusEnum.NOT_PAY.getCode());
    }

    /**
     * 计算订单详情内容
     */
    private void orderDetailInit(OrderPO orderPO) {
        List<OrderDetailPO> orderDetails = orderPO.getOrderDetails();
        final Double[] totalPrice = {0d};
        orderDetails.forEach(detail -> {
            detail.setCreator(WebHelper.getUsername());
            detail.setModifier(WebHelper.getUsername());
            detail.setOrderId(orderPO.getOrderId());
            detail.setStatus(StatusEnum.NORMAL.getCode());
            detail.setTotalPrice(detail.getDishesPrice() * detail.getTotal());
            totalPrice[0] += detail.getTotalPrice();
            orderDetailMapper.insertSelective(detail);
        });
        orderPO.setTotalPrice(totalPrice[0]);
    }

    @Override
    public PageInfo<OrderPO> findList(OrderQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(query.getOrderId())) {
            criteria.andOrderIdEqualTo(query.getOrderId());
        }
        if (!ObjectUtils.isEmpty(query.getStatus())) {
            criteria.andStatusEqualTo(query.getStatus());
        }
        List<OrderPO> list = orderMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public Boolean pay(String orderId) {
        PageHelper.startPage(1, 1);
        OrderExample example = new OrderExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderPO> orderPOS = orderMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(orderPOS)) {
            OrderPO orderPO = orderPOS.get(0);
            orderStatusCheck(OrderEvent.PAYING, OrderStatusEnum.fromCode(orderPO.getStatus()));
            orderPO.setStatus(OrderStatusEnum.PAID.getCode());
            return orderMapper.updateByPrimaryKey(orderPO) == 1;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean comment(CommentVO commentVO) {
        return null;
    }

    @Override
    public OrderPO findOne(OrderQuery query) {
        Assert.isNull(query, "query is null");
        Assert.isNull(query.getOrderId(), "orderId is empty");
        PageHelper.startPage(1, 1);
        OrderExample example = new OrderExample();
        example.createCriteria().andOrderIdEqualTo(query.getOrderId());
        List<OrderPO> orderPOS = orderMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(orderPOS)) {
            return orderPOS.get(0);
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        Assert.isNull(id, "id is null");
        OrderPO orderPO = orderMapper.selectByPrimaryKey(id);
        orderStatusCheck(OrderEvent.DELETE, OrderStatusEnum.fromCode(orderPO.getStatus()));
        orderPO.setStatus(OrderStatusEnum.DELETED.getCode());
        return orderMapper.updateByPrimaryKey(orderPO) == 1;
    }

    /**
     * 计算总营收 与 当日营收
     * @return
     */
    @Override
    public Map statistical() {
//        LocalDate localDate = LocalDate.now().plusDays(-1);
//        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
//        Date yesterday = Date.from(zonedDateTime.toInstant());
//        List<OrderPO> orders = orderMapper.selectByExample(new OrderExample());
//        AtomicReference<Double> totalPrice = new AtomicReference<>(0d);
//        AtomicReference<Double> yesterdayPrice = new AtomicReference<>(0d);
//        Map<String, Object> res = new HashMap<>(8);
//        orders.forEach(order -> {
//            totalPrice.updateAndGet(v -> v + order.getTotalPrice().doubleValue());
////            if (order.getCreated().compareTo())
//        });
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
                if (orderStatus.equals(OrderStatusEnum.ONGOING)) {
                    throw new AppException("当前订单进行中，无法删除", HttpStatus.FORBIDDEN.value());
                }
                break;
            }
            default: {
                throw new AppException("不支持当前操作", HttpStatus.FORBIDDEN.value());
            }
        }
    }
}
