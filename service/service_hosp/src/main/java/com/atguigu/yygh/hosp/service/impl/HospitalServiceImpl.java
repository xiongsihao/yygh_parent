package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.yygh.cmn.client.DictFeignClient;
import com.atguigu.yygh.hosp.repository.HospitalRepository;
import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : xsh
 * @create : 2021-03-31 - 23:13
 * @describe:
 */
@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private DictFeignClient dictFeignClient;

    @Override
    public void save(Map<String, Object> paramMap) {
        log.info(JSONObject.toJSONString(paramMap));
        Hospital hospital = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Hospital.class);
        //判断是否存在
        Hospital targetHospital = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
        if (null != targetHospital) {
            hospital.setStatus(targetHospital.getStatus());
            hospital.setCreateTime(targetHospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {
            //0：未上线 1：已上线
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    /**
     * 查询医院
     *
     * @param hoscode
     * @return
     */
    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    @Override
    public Page<Hospital> selectPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        //0为第一页
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

        //创建实例
        Example<Hospital> example = Example.of(hospital, matcher);
        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);

        //调用远程服务，获取医院等级信息 完整地址封装
        pages.getContent().stream().forEach(item -> {
            this.setHospitalHosType(item);
        });
        return pages;
    }

    //获取list集合，遍历进行医院等级封装 完整地址封装
    private Hospital setHospitalHosType(Hospital hospital) {
        String hostypeString = dictFeignClient.getName("Hostype", hospital.getHostype());
        //查询省市区
        String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getName(hospital.getCityCode());
        String districtString = dictFeignClient.getName(hospital.getDistrictCode());
        hospital.getParam().put("hostypeString", hostypeString);
        hospital.getParam().put("fullAddress", provinceString + cityString + districtString + hospital.getAddress());
        return hospital;
    }

    @Override
    public void updateStatus(String id, Integer status) {
        if(status.intValue() == 0 || status.intValue() == 1) {
            Hospital hospital = hospitalRepository.findById(id).get();
            hospital.setStatus(status);
            hospital.setUpdateTime(new Date());
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Map<String, Object> show(String id) {
        Map<String, Object> result = new HashMap<>();
        Hospital hospital = this.setHospitalHosType(hospitalRepository.findById(id).get());
        result.put("hospital", hospital);
        //单独处理更直观 预约信息
        result.put("bookingRule", hospital.getBookingRule());

        //预约信息已在result内封装了返回结果，hospital内不需要重复返回 置为null
        hospital.setBookingRule(null);
        return result;
    }

    public String getName(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
        if(null != hospital) {
            return hospital.getHosname();
        }
        return "";
    }

    @Override
    public List<Hospital> findByHosname(String hosname) {
        return hospitalRepository.findHospitalByHosnameLike(hosname);
    }

    @Override
    public Map<String, Object> item(String hoscode) {
        Map<String, Object> result = new HashMap<>();
        //医院详情
        Hospital hospital = this.setHospitalHosType(this.getByHoscode(hoscode));
        result.put("hospital", hospital);
        //预约规则
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重复返回
        hospital.setBookingRule(null);
        return result;
    }
}
