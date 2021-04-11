package cn.tianqb.service;

import cn.tianqb.pojo.po.OrderPO;
import cn.tianqb.pojo.query.OrderQuery;
import cn.tianqb.pojo.vo.CommentVO;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author tianqingbo3
 * @date 2021/3/3 21:58
 * @version v1.0
 */
public interface OrderService {

    Boolean create(OrderPO orderPO);

    PageInfo<OrderPO> findList(OrderQuery query);

    Boolean pay(String orderId);

    Boolean comment(CommentVO commentVO);

    OrderPO findOne(OrderQuery query);

    Boolean delete(Integer id);

    Map statistical();

    Boolean update(OrderPO orderPO);

    Boolean statusUpdate(String orderId, Integer code);
}
