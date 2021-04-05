package com.atguigu.yygh.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : xsh
 * @create : 2021-04-05 - 22:18
 * @describe:
 */
@Configuration
@MapperScan("com.atguigu.yygh.user.mapper")
public class UserConfig {
}
