package com.atguigu.yygh.user.service;

import com.atguigu.yygh.model.user.UserInfo;
import com.atguigu.yygh.vo.user.LoginVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author : xsh
 * @create : 2021-04-05 - 22:15
 * @describe:
 */
public interface UserInfoService extends IService<UserInfo> {

    Map<String, Object> loginUser(LoginVo loginVo);
}
