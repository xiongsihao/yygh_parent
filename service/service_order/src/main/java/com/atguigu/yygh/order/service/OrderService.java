package com.atguigu.yygh.order.service;

import com.atguigu.yygh.model.order.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author : xsh
 * @create : 2021-04-14 - 1:11
 * @describe:
 */
public interface OrderService extends IService<OrderInfo> {
    //保存订单
    Long saveOrder(String scheduleId, Long patientId);

    /**
     * 获取订单详情
     */
    OrderInfo getOrderInfo(String id);
}
