package cn.tianqb.service;

import cn.tianqb.common.PageBean;
import cn.tianqb.pojo.po.CategoryPO;
import cn.tianqb.pojo.query.CategoryQuery;

/**
 * @author tianqingbo3
 * @date 2021/3/3 21:54  
 * @version v1.0
 */
interface CategoryService {
    
    Boolean create(CategoryPO categoryPO);

    Boolean delete(Integer id);

    PageBean<CategoryPO> findList(CategoryQuery query);

    CategoryPO group(CategoryQuery query);

    CategoryPO findOne(CategoryQuery query);
}
