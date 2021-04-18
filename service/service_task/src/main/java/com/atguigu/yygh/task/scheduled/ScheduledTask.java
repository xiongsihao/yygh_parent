package com.atguigu.yygh.task.scheduled;

import com.atguigu.common.constant.MqConst;
import com.atguigu.common.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : xsh
 * @create : 2021-04-19 - 1:15
 * @describe:
 */
@Component
@EnableScheduling
public class ScheduledTask {

    @Autowired
    private RabbitService rabbitService;

    /**
     * 每天8点执行 提醒预约的患者就诊
     */
    @Scheduled(cron = "0 0 8 * * ?")
    //@Scheduled(cron = "0/30 * * * * ?")
    public void task1() {
        System.out.println("提醒预约患者就诊短信发送任务执行..");
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_TASK, MqConst.ROUTING_TASK_8, "");
    }
}
