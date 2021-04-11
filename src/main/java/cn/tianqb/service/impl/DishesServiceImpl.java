package cn.tianqb.service.impl;

import cn.tianqb.common.Assert;
import cn.tianqb.enums.DishesStatusEnum;
import cn.tianqb.enums.StatusEnum;
import cn.tianqb.mapper.CategoryMapper;
import cn.tianqb.mapper.DishesMapper;
import cn.tianqb.pojo.example.DishesExample;
import cn.tianqb.pojo.po.CategoryPO;
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
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Boolean create(DishesPO dishes) {
        checkCreate(dishes);
        initCreate(dishes);
        return dishesMapper.insertSelective(dishes) == 1;
    }

    private void checkCreate(DishesPO dishes) {
        Assert.isNull(dishes, "dishes is null");
        Assert.isNull(dishes.getName(), "name is empty");
        Assert.isNull(dishes.getUrl(), "url is empty");
        Assert.isNull(dishes.getPrice(), "price is empty");
        Assert.isNull(dishes.getCostPrice(), "costPrice is empty");
        Assert.isNull(dishes.getCategoryId(), "categoryId is empty");
        Assert.isNull(dishes.getUnit(), "unit is empty");
    }

    private void initCreate(DishesPO dishes) {
        dishes.setStatus(StatusEnum.NORMAL.getCode());
        dishes.setCreator(WebHelper.getUsername());
        dishes.setModifier(WebHelper.getUsername());
        CategoryPO category = categoryMapper.selectByPrimaryKey(dishes.getCategoryId());
        Assert.isNull(category, "category is null");
        dishes.setCategory(category.getName());
    }

    @Override
    public Boolean delete(Integer id) {
        Assert.isNull(id, "id is empty");
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
        example.setOrderByClause("created desc");
        DishesExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(query.getName())) {
            criteria.andNameLike("%" + query.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(query.getCategoryId())) {
            criteria.andCategoryIdEqualTo(query.getCategoryId());
        }
        criteria.andStatusNotEqualTo(DishesStatusEnum.DELETED.getCode());
        if (!ObjectUtils.isEmpty(query.getStatus())) {
            criteria.andStatusEqualTo(query.getStatus());
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

    @Override
    public Boolean online(Integer id) {
        Assert.isNull(id, "id is empty");
        DishesPO dishes = new DishesPO();
        dishes.setId(id);
        dishes.setStatus(DishesStatusEnum.ONLINE.getCode());
        return dishesMapper.updateByPrimaryKeySelective(dishes) == 1;
    }

    @Override
    public Boolean offline(Integer id) {
        Assert.isNull(id, "id is empty");
        DishesPO dishes = new DishesPO();
        dishes.setId(id);
        dishes.setStatus(DishesStatusEnum.OFFLINE.getCode());
        return dishesMapper.updateByPrimaryKeySelective(dishes) == 1;
    }
}
