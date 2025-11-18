package com.timberg.gyuan.mapper;

import com.timberg.gyuan.model.DailyVisit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

@Mapper
public interface DailyVisitMapper {
    
    /**
     * 根据日期查询访问记录
     */
    DailyVisit findByVisitDate(@Param("visitDate") LocalDate visitDate);
    
    /**
     * 插入新的访问记录
     */
    int insert(DailyVisit dailyVisit);
    
    /**
     * 更新访问量
     */
    int updateVisitCount(@Param("visitDate") LocalDate visitDate, @Param("visitCount") Long visitCount);
    
    /**
     * 获取总访问量
     */
    Long getTotalVisits();
}
