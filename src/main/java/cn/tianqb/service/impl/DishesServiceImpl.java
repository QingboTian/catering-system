package cn.tianqb.service.impl;

import cn.tianqb.common.Assert;
import cn.tianqb.enums.StatusEnum;
import cn.tianqb.mapper.DishesMapper;
import cn.tianqb.pojo.example.DishesExample;
import cn.tianqb.pojo.po.DishesPO;
import cn.tianqb.pojo.query.DishesQuery;
import cn.tianqb.service.DishesService;
import cn.tianqb.utils.WebHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/5 16:14
 */
@Service
@Slf4j
public class DishesServiceImpl implements DishesService {

    @Autowired
    private DishesMapper dishesMapper;

    @Override
    public Boolean create(DishesPO dishes) {
        dishes.setStatus(StatusEnum.NORMAL.getCode());
        checkCreate(dishes);
        return dishesMapper.insertSelective(dishes) == 1;
    }

    private void checkCreate(DishesPO dishes) {
        Assert.isNull(dishes, "dishes is null");
        Assert.isNull(dishes.getName(), "name is empty");
        Assert.isNull(dishes.getUrl(), "url is empty");
        Assert.isNull(dishes.getPrice(), "price is empty");
    }

    @Override
    public Boolean delete(Integer id) {
        DishesPO dishes = dishesMapper.selectByPrimaryKey(id);
        dishes.setStatus(StatusEnum.DELETED.getCode());
        dishes.setModifier(WebHelper.getUsername());
        return dishesMapper.updateByPrimaryKey(dishes) == 1;
    }

    @Override
    public Boolean update(DishesPO dishes) {
        checkCreate(dishes);
        dishes.setModifier(WebHelper.getUsername());
        return dishesMapper.updateByPrimaryKey(dishes) == 1;
    }

    @Override
    public PageInfo<DishesPO> findList(DishesQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        DishesExample example = new DishesExample();
        DishesExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(query.getName())) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(query.getCategoryId())) {
            criteria.andCategoryIdEqualTo(query.getCategoryId());
        }
        // low high price

        List<DishesPO> list = dishesMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public DishesPO findOne(DishesQuery query) {
        Assert.isNull(query, "query is null");
        Assert.isNull(query.getId(), "id is empty");
        return dishesMapper.selectByPrimaryKey(query.getId());
    }
}
