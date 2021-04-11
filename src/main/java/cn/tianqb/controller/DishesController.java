package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.DishesPO;
import cn.tianqb.pojo.query.DishesQuery;
import cn.tianqb.service.DishesService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 21:21
 */
@RestController
@Slf4j
@RequestMapping("/api/dishes")
public class DishesController {

    @Autowired
    private DishesService dishesService;

    @PostMapping("/create")
    public WebResult<Boolean> create(@RequestBody DishesPO dishes) {
        return WebResult.ok(dishesService.create(dishes));
    }

    @PostMapping("/delete")
    public WebResult<Boolean> delete(Integer id) {
        return WebResult.ok(dishesService.delete(id));
    }

    @PostMapping("/update")
    public WebResult<Boolean> update(@RequestBody DishesPO dishes) {
        return WebResult.ok(dishesService.update(dishes));
    }

    @GetMapping("/list")
    public WebResult<PageInfo<DishesPO>> findList(DishesQuery query) {
        return WebResult.ok(dishesService.findList(query));
    }

    @PostMapping("/online")
    public WebResult<Boolean> online(Integer id) {
        return WebResult.ok(dishesService.online(id));
    }

    @PostMapping("/offline")
    public WebResult<Boolean> offline(Integer id) {
        return WebResult.ok(dishesService.offline(id));
    }
}
