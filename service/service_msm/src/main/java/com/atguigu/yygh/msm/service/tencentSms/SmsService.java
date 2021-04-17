package com.atguigu.yygh.msm.service.tencentSms;

import com.atguigu.yygh.vo.msm.MsmVo;

/**
 * @author : xsh
 * @create : 2021-04-11 - 19:56
 * @describe:
 */
public interface SmsService {

    /**
     * 发送短信
     * @param mobile
     * @param content
     * @return
     */
    default void send(String mobile, String content) {
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @param code
     * @param effectiveTime
     * @return
     */
    boolean send(String mobile, String code, int effectiveTime);

    /**
     * 在控制台输出验证码，模拟发送短信
     * @param mobile
     * @param code
     * @return
     */
    boolean sendOnConsole(String mobile, Object code);

    //MQ使用发送短信
    boolean send(MsmVo msmVo);
}
