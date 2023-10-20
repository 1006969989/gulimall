package com.yuan.gulimall.product.dao;

import com.yuan.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author yuan
 * @email 1006969989@qq.com
 * @date 2023-10-17 00:39:56
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
