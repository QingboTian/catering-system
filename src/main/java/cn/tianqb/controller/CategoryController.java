package cn.tianqb.controller;

import cn.tianqb.common.PageBean;
import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.CategoryPO;
import cn.tianqb.pojo.query.CategoryQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 21:22
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @PostMapping("/create")
    public WebResult<Boolean> create(CategoryPO categoryPO) {
        return null;
    }

    @PostMapping("/delete")
    public WebResult<Boolean> delete(Integer id) {
        return null;
    }

    @GetMapping("/list")
    public WebResult<PageBean<CategoryPO>> findList(CategoryQuery query) {
        return null;
    }

    @GetMapping("/list/group")
    public WebResult<CategoryPO> group(CategoryQuery query) {
        return null;
    }

    @GetMapping("/get")
    public WebResult<CategoryPO> findOne(CategoryQuery query) {
        return null;
    }

}
