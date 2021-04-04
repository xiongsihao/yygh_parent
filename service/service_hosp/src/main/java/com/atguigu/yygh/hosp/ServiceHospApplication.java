package com.atguigu.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : xsh
 * @create : 2021-03-26 - 0:37
 * @describe:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
@EnableFeignClients(basePackages = "com.atguigu")
@EnableDiscoveryClient
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
