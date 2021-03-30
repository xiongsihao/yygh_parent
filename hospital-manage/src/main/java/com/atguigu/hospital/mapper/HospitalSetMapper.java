package com.atguigu.hospital.mapper;

import com.atguigu.hospital.model.HospitalSet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HospitalSetMapper extends BaseMapper<HospitalSet> {

}
