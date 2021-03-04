package cn.tianqb.service;

import cn.tianqb.pojo.po.CategoryPO;
import cn.tianqb.pojo.query.CategoryQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/4 20:18
 */
public interface CategoryService {

    Boolean create(CategoryPO categoryPO);

    Boolean delete(Integer id) ;

    PageInfo<CategoryPO> findList(CategoryQuery query);

    List<CategoryPO> group(CategoryQuery query);

    CategoryPO findOne(CategoryQuery query);
}
