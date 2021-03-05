package cn.tianqb.controller;

import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.DishesPO;
import cn.tianqb.pojo.query.DishesQuery;
import cn.tianqb.service.DishesService;
import cn.tianqb.utils.WebHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DishesService dishesService;

    @PostMapping("/create")
    public WebResult<Boolean> create(DishesPO dishesPO) {
        dishesPO.setCreator(WebHelper.getUsername());
        dishesPO.setModifier(WebHelper.getUsername());
        return WebResult.ok(dishesService.create(dishesPO));
    }

    @PostMapping("/delete")
    public WebResult<Boolean> delete(Integer id) {
        return WebResult.ok(dishesService.delete(id));
    }

    @PostMapping("/update")
    public WebResult<Boolean> update(DishesPO dishesPO) {
        return WebResult.ok(dishesService.update(dishesPO));
    }

    @GetMapping("/list")
    public WebResult<PageInfo<DishesPO>> findList(DishesQuery query) {
        return WebResult.ok(dishesService.findList(query));
    }

    @GetMapping("/get")
    public WebResult<DishesPO> findOne(DishesQuery query) {
        return WebResult.ok(dishesService.findOne(query));
    }
}
