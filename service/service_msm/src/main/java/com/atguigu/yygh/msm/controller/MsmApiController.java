package com.atguigu.yygh.msm.controller;

import com.atguigu.yygh.common.result.Result;

import com.atguigu.yygh.msm.service.aliyunSms.MsmService;
import com.atguigu.yygh.msm.service.aliyunSms.RandomUtil;
import com.atguigu.yygh.msm.service.tencentSms.SmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author : xsh
 * @create : 2021-04-11 - 19:31
 * @describe:
 */
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;
    @Autowired
    private SmsService smsService;


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok().message("此号码五分钟内已发送过验证码");
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = RandomUtil.getSixBitRandom();
        //调用service方法，通过整合短信服务进行发送

        //阿里云发送验证码
        //boolean isSend = msmService.send(phone,code);
        //腾讯云发送验证码
        //boolean isSend = smsService.send(phone, code, 5);
        //不实际发送验证码，在控制台输出验证码
        boolean isSend = smsService.sendOnConsole(phone, code);
        //生成验证码放到redis里面，设置有效时间
        if(isSend) {
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.fail().message("发送短信失败");
        }
    }
}
