package cn.tianqb.controller;

import cn.tianqb.common.Assert;
import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.DishesPO;
import cn.tianqb.pojo.po.OrderDetailPO;
import cn.tianqb.pojo.query.DishesQuery;
import cn.tianqb.service.DishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 购物车
 *
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/6 13:37
 */
@RestController
@Slf4j
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private DishesService dishesService;

    /**
     * @param request
     * @param dishesId
     * @param num      增加或减少的数量
     * @return
     */
    @PostMapping("/addOrReduce")
    public WebResult addShoppingCart(HttpServletRequest request, Integer dishesId, Integer num) {
        Assert.isNull(dishesId, "dishesId is empty");
        Assert.isNull(num, "num is empty");
        OrderDetailPO detail = new OrderDetailPO();

        DishesQuery query = new DishesQuery();
        query.setId(dishesId);
        DishesPO dishes = dishesService.findOne(query);
        Assert.isNull(dishes, "dishes is null");

        HttpSession session = request.getSession();
        String token = request.getHeader("token");
        String cartKey = token + "_cart";
        List<OrderDetailPO> cart = (List<OrderDetailPO>) session.getAttribute(cartKey);
        if (cart == null) {
            cart = new ArrayList<>();
            if (num > 0) {
                buildOrderDetail(detail, dishes, num);
                cart.add(detail);
                session.setAttribute(cartKey, cart);
            }
        } else {
            // 购物车是否存在该菜品标志位
            boolean flag = true;
            Iterator<OrderDetailPO> iterator = cart.iterator();
            while (iterator.hasNext()) {
                OrderDetailPO orderDetail = iterator.next();
                // 存在该菜品 直接修改total and totalPrice
                if (dishesId.equals(orderDetail.getDishesId())) {
                    int total = orderDetail.getTotal() + num;
                    // 若当前total > 0 则进行数据修改 否则对该菜品从购物车进行删除
                    if (total > 0) {
                        buildOrderDetail(orderDetail, dishes, total);
                    } else {
                        iterator.remove();
                    }
                    flag = false;
                }
            }
            if (flag) {
                if (num > 0) {
                    buildOrderDetail(detail, dishes, num);
                    cart.add(detail);
                    session.setAttribute(cartKey, cart);
                }
            }

        }
        return WebResult.ok();
    }

    /**
     * 构建购物车商品条目
     *
     * @param detail 条目信息
     * @param dishes 菜品信息
     * @param total  数量
     */
    private void buildOrderDetail(OrderDetailPO detail, DishesPO dishes, Integer total) {
        detail.setTotal(total);
        detail.setTotalPrice(dishes.getPrice() * total);
        detail.setUrl(dishes.getUrl());
        detail.setDishesPrice(dishes.getPrice());
        detail.setDishesName(dishes.getName());
        detail.setDishesId(dishes.getId());
    }

    @GetMapping("/list")
    public WebResult list(HttpServletRequest request) {
        String cartKey = request.getHeader("token") + "_cart";
        List<OrderDetailPO> cart = (List<OrderDetailPO>) request.getSession().getAttribute(cartKey);
        if (cart != null) {
            return WebResult.ok(cart);
        }
        return WebResult.ok(new ArrayList<>());
    }

    /**
     * @param request
     * @param dishesId 菜品Id
     * @return
     */
    @PostMapping("/delete")
    public WebResult delete(HttpServletRequest request, Integer dishesId) {
        String cartKey = request.getHeader("token") + "_cart";
        HttpSession session = request.getSession();
        List<OrderDetailPO> cart = (List<OrderDetailPO>) session.getAttribute(cartKey);
        if (cart != null) {
            cart.removeIf(orderDetail -> dishesId.equals(orderDetail.getDishesId()));
        }
        return WebResult.ok();
    }
}
