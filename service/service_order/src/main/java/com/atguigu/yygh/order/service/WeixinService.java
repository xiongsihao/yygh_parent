package com.atguigu.yygh.order.service;

import java.util.Map;

/**
 * @author : xsh
 * @create : 2021-04-18 - 18:35
 * @describe:
 */
public interface WeixinService {
    /**
     * 根据订单号下单，生成支付链接
     */
    Map createNative(Long orderId);

    /**
     * 根据订单号去微信第三方查询支付状态
     */
    Map queryPayStatus(Long orderId, String paymentType);

    /***
     * 微信退款接口
     * @param orderId
     * @return
     */
    Boolean refund(Long orderId);
}
