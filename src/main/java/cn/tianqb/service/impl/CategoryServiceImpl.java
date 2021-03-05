package cn.tianqb.service.impl;

import cn.tianqb.enums.StatusEnum;
import cn.tianqb.mapper.CategoryMapper;
import cn.tianqb.pojo.example.CategoryExample;
import cn.tianqb.pojo.po.CategoryPO;
import cn.tianqb.pojo.query.CategoryQuery;
import cn.tianqb.service.CategoryService;
import cn.tianqb.utils.WebHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * @author tianqingbo3
 * @version 1.0
 * @date 2021/3/4 20:19
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Boolean create(CategoryPO categoryPO) {
        categoryPO.setStatus(StatusEnum.NORMAL.getCode());
        if (ObjectUtils.isEmpty(categoryPO.getParentId()) || categoryPO.getParentId() == 0) {
            categoryPO.setLevel(1);
        } else {
            CategoryPO category = categoryMapper.selectByPrimaryKey(categoryPO.getParentId());
            categoryPO.setLevel(category.getLevel() + 1);
        }
        return categoryMapper.insertSelective(categoryPO) == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        CategoryPO category = categoryMapper.selectByPrimaryKey(id);
        category.setModifier(WebHelper.getUsername());
        category.setStatus(StatusEnum.DELETED.getCode());
        return categoryMapper.updateByPrimaryKey(category) == 1;
    }

    @Override
    public PageInfo<CategoryPO> findList(CategoryQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        List<CategoryPO> list = categoryMapper.selectByExample(buildExample(query));
        return new PageInfo<>(list);
    }

    private CategoryExample buildExample(CategoryQuery query) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(query.getParentId())) {
            criteria.andParentIdEqualTo(query.getParentId());
        }
        if (!ObjectUtils.isEmpty(query.getLevel())) {
            criteria.andLevelEqualTo(query.getLevel());
        }
        if (!ObjectUtils.isEmpty(query.getName())){
            criteria.andNameLike("%" + query.getName() + "%");
        }
        criteria.andStatusEqualTo(StatusEnum.NORMAL.getCode());
        return example;
    }

    @Override
    public List<CategoryPO> group(CategoryQuery query) {
        CategoryExample example = buildExample(query);
        List<CategoryPO> list = categoryMapper.selectByExample(example);
        list.forEach(category -> {
            CategoryExample childrenExample = new CategoryExample();
            childrenExample.createCriteria()
                    .andParentIdEqualTo(category.getId())
                    .andStatusEqualTo(StatusEnum.NORMAL.getCode());
            List<CategoryPO> children = categoryMapper.selectByExample(childrenExample);
            category.setChildren(children);
        });
        return list;
    }

    @Override
    public CategoryPO findOne(CategoryQuery query) {
        return categoryMapper.selectByPrimaryKey(query.getId());
    }
}
