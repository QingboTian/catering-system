package cn.tianqb.controller;

import cn.tianqb.common.PageBean;
import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.OrderPO;
import cn.tianqb.pojo.query.OrderQuery;
import cn.tianqb.pojo.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/create")
    public WebResult<Boolean> create(OrderPO orderPO) {
        return null;
    }

    @GetMapping("/list")
    public WebResult<PageBean<OrderPO>> findList(OrderQuery query) {
        return null;
    }

    @GetMapping("/pay")
    public WebResult<Boolean> pay(String orderId) {
        return null;
    }

    @GetMapping("/comment")
    public WebResult<Boolean> comment(CommentVO commentVO) {
        return null;
    }

    @GetMapping("/get")
    public WebResult<OrderPO> findOne(OrderQuery query) {
        return null;
    }

    @GetMapping("/delete")
    public WebResult<Boolean> delete(Integer id) {
        return null;
    }

    @GetMapping("/statistical")
    public WebResult<Map> statistical() {
        return null;
    }

}
