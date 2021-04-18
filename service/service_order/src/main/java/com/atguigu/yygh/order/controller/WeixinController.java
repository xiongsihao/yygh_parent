package com.atguigu.yygh.order.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.order.service.WeixinService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xsh
 * @create : 2021-04-18 - 18:36
 * @describe:
 */
@RestController
@RequestMapping("/api/order/weixin")
public class WeixinController {
    @Autowired
    private WeixinService weixinPayService;
    /**
     * 下单 生成微信支付扫描二维码
     */
    @GetMapping("/createNative/{orderId}")
    public Result createNative(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @PathVariable("orderId") Long orderId) {
        return Result.ok(weixinPayService.createNative(orderId));
    }
}
