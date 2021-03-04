package cn.tianqb.controller;

import cn.tianqb.common.PageBean;
import cn.tianqb.common.WebResult;
import cn.tianqb.pojo.po.CategoryPO;
import cn.tianqb.pojo.query.CategoryQuery;
import cn.tianqb.service.CategoryService;
import cn.tianqb.utils.WebHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/2 21:22
 */
@RestController
@Slf4j
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public WebResult<Boolean> create(CategoryPO categoryPO) {
        categoryPO.setCreator(WebHelper.getUsername());
        categoryPO.setModifier(WebHelper.getUsername());
        return WebResult.ok(categoryService.create(categoryPO));
    }

    @PostMapping("/delete")
    public WebResult<Boolean> delete(Integer id) {
        return WebResult.ok(categoryService.delete(id));
    }

    @GetMapping("/list")
    public WebResult<PageInfo<CategoryPO>> findList(CategoryQuery query) {
        return WebResult.ok(categoryService.findList(query));
    }

    @GetMapping("/list/group")
    public WebResult<List<CategoryPO>> group(CategoryQuery query) {
        return WebResult.ok(categoryService.group(query));
    }

    @GetMapping("/get")
    public WebResult<CategoryPO> findOne(CategoryQuery query) {
        return WebResult.ok(categoryService.findOne(query));
    }

}
