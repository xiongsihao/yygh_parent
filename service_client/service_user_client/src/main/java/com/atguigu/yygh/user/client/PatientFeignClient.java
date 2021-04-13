package com.atguigu.yygh.user.client;

import com.atguigu.yygh.model.user.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : xsh
 * @create : 2021-04-14 - 1:19
 * @describe:
 */
@FeignClient(value = "service-user")
@Repository
public interface PatientFeignClient {
    //获取就诊人
    @GetMapping("/api/user/patient/inner/get/{id}")
    Patient getPatient(@PathVariable("id") Long id);
}
