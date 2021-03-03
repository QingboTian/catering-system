package cn.tianqb.service;

import cn.tianqb.common.PageBean;
import cn.tianqb.pojo.po.DishesPO;
import cn.tianqb.pojo.query.DishesQuery;

/**
 * @author tianqingbo3
 * @date 2021/3/3 21:56  
 * @version v1.0
 */
interface DishesService {

    Boolean create(DishesPO dishesPO);

    Boolean delete(Integer id);

    Boolean update(DishesPO dishesPO);

    PageBean<DishesPO> findList(DishesQuery query);

    DishesPO findOne(DishesQuery query);
}
