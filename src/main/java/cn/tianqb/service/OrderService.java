package cn.tianqb.service;

import cn.tianqb.common.PageBean;
import cn.tianqb.pojo.po.Order;
import cn.tianqb.pojo.query.OrderQuery;
import cn.tianqb.pojo.vo.CommentVO;

import java.util.Map;

/**
 * @author tianqingbo3
 * @date 2021/3/3 21:58  
 * @version v1.0
 */
interface OrderService {

    Boolean create(Order order);

    PageBean<Order> findList(OrderQuery query);

    Boolean pay(String orderId);

    Boolean comment(CommentVO commentVO);

    Order findOne(OrderQuery query);

    Boolean delete(Integer id);

    Map statistical();
}
