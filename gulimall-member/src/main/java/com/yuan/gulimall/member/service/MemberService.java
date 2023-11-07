package com.yuan.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.common.utils.PageUtils;
import com.yuan.gulimall.member.entity.MemberEntity;
import com.yuan.gulimall.member.exception.PhoneException;
import com.yuan.gulimall.member.exception.UsernameException;
import com.yuan.gulimall.member.vo.MemberUserLoginVo;
import com.yuan.gulimall.member.vo.MemberUserRegisterVo;

import java.util.Map;

/**
 * 会员
 *
 * @author yuan
 * @email 1006969989@qq.com
 * @date 2023-10-17 15:41:46
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 用户注册
     * @param vo
     */
    void register(MemberUserRegisterVo vo);

    /**
     * 判断邮箱是否重复
     * @param phone
     * @return
     */
    void checkPhoneUnique(String phone) throws PhoneException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UsernameException;
    /**
     * 用户登录
     * @param vo
     * @return
     */
    MemberEntity login(MemberUserLoginVo vo);
}

