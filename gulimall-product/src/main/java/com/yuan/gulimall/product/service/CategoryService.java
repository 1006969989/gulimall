package com.yuan.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.common.utils.PageUtils;
import com.yuan.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author yuan
 * @email 1006969989@qq.com
 * @date 2023-10-17 00:39:56
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeByCatIds(List<Long> asList);
    Long[] findCatelogPath(Long catelogId);

    void updateCascade(CategoryEntity category);
}

