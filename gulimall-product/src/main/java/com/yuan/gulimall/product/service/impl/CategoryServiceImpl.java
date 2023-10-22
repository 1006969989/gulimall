package com.yuan.gulimall.product.service.impl;

import com.yuan.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.common.utils.PageUtils;
import com.yuan.common.utils.Query;

import com.yuan.gulimall.product.dao.CategoryDao;
import com.yuan.gulimall.product.entity.CategoryEntity;
import com.yuan.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查询所有菜单
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);
        //2.设置子菜单
        List<CategoryEntity> treeEntity = categoryEntityList.stream().filter((category) ->{
             return category.getParentCid() == 0 ;
        }).map((category) -> {
            category.setChildren(setChildren(category, categoryEntityList));
            return category;
        }).sorted((category1, category2) -> {
            return (category1.getSort() == null ? 0 : category1.getSort()) - (category2.getSort() == null ? 0 : category2.getSort());
        }).collect(Collectors.toList());
        return treeEntity;
    }

    @Override
    public void removeByCatIds(List<Long> asList) {

        //TODO 逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    private List<CategoryEntity> setChildren(CategoryEntity root,List<CategoryEntity> all){

        List<CategoryEntity> categoryList = all.stream().filter((category) -> {
            return category.getParentCid().equals(root.getCatId());
        }).map((category) -> {
            category.setChildren(setChildren(category, all));
            return category;
        }).sorted((category1, category2) -> {
            return (category1.getSort() == null ? 0 : category1.getSort()) - (category2.getSort() == null ? 0 : category2.getSort());
        }).collect(Collectors.toList());
        return categoryList;
    }

    //[2,29,20]
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        //递归查询是否还有父节点
        List<Long> parentPath = findParentPath(catelogId, paths);
        //进行一个逆序排列
        Collections.reverse(parentPath);
        return (Long[]) parentPath.toArray(new Long[parentPath.size()]);
    }

    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.baseMapper.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        //1、收集当前节点id
        paths.add(catelogId);
        //根据当前分类id查询信息
        CategoryEntity byId = this.getById(catelogId);
        //如果当前不是父分类
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }
}
