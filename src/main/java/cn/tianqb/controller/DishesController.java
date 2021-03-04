package cn.tianqb.controller;

import cn.tianqb.common.PageBean;
import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.DishesPO;
import cn.tianqb.pojo.query.DishesQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/create")
    public WebResult<Boolean> create(DishesPO dishesPO) {
        return null;
    }

    @PostMapping("/delete")
    public WebResult<Boolean> delete(Integer id) {
        return null;
    }

    @PostMapping("/update")
    public WebResult<Boolean> update(DishesPO dishesPO) {
        return null;
    }

    @GetMapping("/list")
    public WebResult<PageBean<DishesPO>> findList(DishesQuery query) {
        return null;
    }

    @GetMapping("/get")
    public WebResult<DishesPO> findOne(DishesQuery query) {
        return null;
    }
}
