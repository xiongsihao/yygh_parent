package com.atguigu.yygh.user.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.user.service.UserInfoService;
import com.atguigu.yygh.vo.user.LoginVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : xsh
 * @create : 2021-04-05 - 22:15
 * @describe:
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        //loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> info = userInfoService.loginUser(loginVo);
        return Result.ok(info);
    }

}
