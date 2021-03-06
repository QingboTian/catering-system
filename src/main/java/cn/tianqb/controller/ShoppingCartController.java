package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.OrderDetailPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 购物车
 *
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/6 13:37
 */
@RestController
@Slf4j
@RequestMapping("/cart")
public class ShoppingCartController {

    /**
     *
     * @param request
     * @param detail
     * @param num 增加或减少的数量
     * @return
     */
    @PostMapping("/addOrReduce")
    public WebResult addShoppingCart(HttpServletRequest request, OrderDetailPO detail, Integer num) {
        HttpSession session = request.getSession();
        String token = request.getHeader("token");
        String cartKey = token + "_cart";
        List<OrderDetailPO> cart = (List<OrderDetailPO>) session.getAttribute(cartKey);
        if (ObjectUtils.isEmpty(cart)) {
            cart = new ArrayList<>();
            if (num > 0) {
                detail.setTotal(num);
                detail.setTotalPrice(num * detail.getDishesPrice());
                cart.add(detail);
            }
        } else {
            AtomicReference<Boolean> flag = new AtomicReference<>(true);
            cart.forEach(orderDetail -> {
                if (detail.getDishesId().equals(orderDetail.getDishesId())) {
                    int total = orderDetail.getTotal() + num;
                    if (total > 0) {
                        orderDetail.setTotal(total);
                        orderDetail.setTotalPrice(detail.getDishesPrice() * total);
                    } else {
                        delete(request, detail.getDishesId());
                    }
                    flag.set(false);
                }
            });
            if (flag.get()) {
                detail.setTotal(num);
                detail.setTotalPrice(num * detail.getDishesPrice());
                cart.add(detail);
            }
        }
        session.setAttribute(cartKey, cart);
        return WebResult.ok();
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
     *
     * @param request
     * @param dishesId 菜品Id
     * @return
     */
    @PostMapping("/delete")
    public WebResult delete(HttpServletRequest request, Integer dishesId) {
        String cartKey = request.getHeader("token") + "_cart";
        List<OrderDetailPO> cart = (List<OrderDetailPO>) request.getSession().getAttribute(cartKey);
        if (cart != null) {
            List<OrderDetailPO> list = cart.stream()
                    .filter(detail -> !detail.getDishesId().equals(dishesId))
                    .collect(Collectors.toList());
            request.getSession().setAttribute(cartKey, list);
        }
        return WebResult.ok();
    }
}
