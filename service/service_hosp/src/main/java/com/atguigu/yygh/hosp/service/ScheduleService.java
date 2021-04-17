package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Schedule;
import com.atguigu.yygh.vo.hosp.ScheduleOrderVo;
import com.atguigu.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author : xsh
 * @create : 2021-04-01 - 1:18
 * @describe:
 */
public interface ScheduleService {

    /**
     * 上传排班信息
     *
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 分页查询
     *
     * @param page            当前页码
     * @param limit           每页记录数
     * @param scheduleQueryVo 查询条件
     * @return
     */
    Page<Schedule> selectPage(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo);

    /**
     * 删除科室
     *
     * @param hoscode
     * @param hosScheduleId
     */
    void remove(String hoscode, String hosScheduleId);

    //根据医院编号 和 科室编号 ，查询排班规则数据
    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    //根据医院编号 、科室编号和工作日期，查询排班详细信息
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);

    /**
     * 获取排班可预约日期数据
     *
     * @param page
     * @param limit
     * @param hoscode
     * @param depcode
     * @return
     */
    Map<String, Object> getBookingScheduleRule(int page, int limit, String hoscode, String depcode);

    /**
     * 根据id获取排班
     * @param id
     * @return
     */
    Schedule getById(String id);

    //根据排班id获取预约下单数据
    ScheduleOrderVo getScheduleOrderVo(String scheduleId);

    /**
     * MQ修改排班信息
     */
    void update(Schedule schedule);
}
