package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author : xsh
 * @create : 2021-03-31 - 23:13
 * @describe:
 */
public interface HospitalService {

    void save(Map<String, Object> paramMap);

    /**
     * 查询医院
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);

    /**
     * 分页查询
     * @param page 当前页码
     * @param limit 每页记录数
     * @param hospitalQueryVo 查询条件
     * @return
     */
    Page<Hospital> selectPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    /**
     * 更新上线状态
     */
    void updateStatus(String id, Integer status);

    /**
     * 医院详情
     * @param id
     * @return
     */
    Map<String, Object> show(String id);

    /**
     * 根据医院编号获取医院名称接口
     * @param hoscode
     * @return
     */
    String getName(String hoscode);

    /**
     * 根据医院名称获取医院列表
     */
    List<Hospital> findByHosname(String hosname);

    /**
     * 医院预约挂号详情
     */
    Map<String, Object> item(String hoscode);
}
