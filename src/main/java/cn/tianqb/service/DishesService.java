package cn.tianqb.service;

import cn.tianqb.pojo.po.DishesPO;
import cn.tianqb.pojo.query.DishesQuery;
import com.github.pagehelper.PageInfo;

/**
 * @author tianqingbo3
 * @date 2021/3/3 21:56
 * @version v1.0
 */
public interface DishesService {

    Boolean create(DishesPO dishesPO);

    Boolean delete(Integer id);

    Boolean update(DishesPO dishesPO);

    PageInfo<DishesPO> findList(DishesQuery query);

    DishesPO findOne(DishesQuery query);
}
