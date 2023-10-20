package com.yuan.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 品牌
 * 
 * @author yuan
 * @email 1006969989@qq.com
 * @date 2023-10-17 00:39:56
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotNull(message = "品牌名不为空")
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotNull(message = "品牌logo地址不为空")
	@URL(message = "logo必须为URL地址")
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotNull(message = "检索首字母不为空")
	@Pattern(regexp = "/^[a-zA-Z]$/",message = "首字母必须为A-Z或a-z")
	private String firstLetter;
	/**
	 * 排序
	 */
	@NotNull(message = "排序不为空")
	@Min(message = "排序字段为非负整数", value = 0L)
	private Integer sort;

}
