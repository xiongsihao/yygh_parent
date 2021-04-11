package com.atguigu.yygh.msm.service.aliyunSms;

/**
 * @author : xsh
 * @create : 2021-04-11 - 19:32
 * @describe:
 */
public interface MsmService {

    //发送手机验证码
    boolean send(String phone, String code);
}
