package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.enums.OrderStatusEnum;
import cn.tianqb.pojo.po.OrderPO;
import cn.tianqb.pojo.query.OrderQuery;
import cn.tianqb.pojo.vo.CommentVO;
import cn.tianqb.service.OrderService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 21:23
 */
@RestController
@Slf4j
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public WebResult<Boolean> create(@RequestBody OrderPO order) {
        return WebResult.ok(orderService.create(order));
    }

    @GetMapping("/list")
    public WebResult<PageInfo<OrderPO>> findList(OrderQuery query) {
        return WebResult.ok(orderService.findList(query));
    }

    @PostMapping("/pay")
    public WebResult<Boolean> pay(String orderId) {
        return WebResult.ok(orderService.pay(orderId));
    }

    @PostMapping("/comment")
    public WebResult<Boolean> comment(CommentVO commentVO) {
        return WebResult.ok(orderService.comment(commentVO));
    }

    @GetMapping("/get")
    public WebResult<OrderPO> findOne(OrderQuery query) {
        return WebResult.ok(orderService.findOne(query));
    }

    @PostMapping("/delete")
    public WebResult<Boolean> delete(Integer id) {
        return WebResult.ok(orderService.delete(id));
    }

    @GetMapping("/statistical")
    public WebResult<Map> statistical() {
        return WebResult.ok(orderService.statistical());
    }

    @PostMapping("/done")
    public WebResult<Boolean> done(String orderId) {
        return WebResult.ok(orderService.statusUpdate(orderId, OrderStatusEnum.DONE.getCode()));
    }

}
