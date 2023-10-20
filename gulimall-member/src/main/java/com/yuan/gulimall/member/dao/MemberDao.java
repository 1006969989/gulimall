package com.yuan.gulimall.member.dao;

import com.yuan.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author yuan
 * @email 1006969989@qq.com
 * @date 2023-10-17 15:41:46
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
