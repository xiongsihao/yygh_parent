package com.atguigu.yygh.order.service;

import com.atguigu.yygh.model.order.OrderInfo;
import com.atguigu.yygh.order.mapper.OrderInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : xsh
 * @create : 2021-04-14 - 1:11
 * @describe:
 */
@Service
public class OrderServiceImpl extends
        ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderService {
    //保存订单
    @Override
    public Long saveOrder(String scheduleId, Long patientId) {
        return null;
    }
}
