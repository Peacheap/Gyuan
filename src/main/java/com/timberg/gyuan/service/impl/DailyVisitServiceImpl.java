package com.timberg.gyuan.service.impl;

import com.timberg.gyuan.dto.DailyVisitResp;
import com.timberg.gyuan.mapper.DailyVisitMapper;
import com.timberg.gyuan.model.DailyVisit;
import com.timberg.gyuan.service.DailyVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DailyVisitServiceImpl implements DailyVisitService {

    @Autowired
    private DailyVisitMapper dailyVisitMapper;

    @Override
    public void incrementVisit() {
        LocalDate today = LocalDate.now();
        
        // 查询今天的访问记录
        DailyVisit existingVisit = dailyVisitMapper.findByVisitDate(today);
        
        if (existingVisit != null) {
            // 如果存在，访问量+1
            Long newCount = existingVisit.getVisitCount() + 1;
            dailyVisitMapper.updateVisitCount(today, newCount);
        } else {
            // 如果不存在，创建新记录
            DailyVisit newVisit = new DailyVisit();
            newVisit.setVisitDate(today);
            newVisit.setVisitCount(1L);
            newVisit.setLastUpdate(LocalDateTime.now());
            dailyVisitMapper.insert(newVisit);
        }
    }

    @Override
    public DailyVisitResp getVisitStats() {
        // 获取总访问量
        Long totalVisits = dailyVisitMapper.getTotalVisits();
        
        // 获取今日访问量
        LocalDate today = LocalDate.now();
        DailyVisit todayVisit = dailyVisitMapper.findByVisitDate(today);
        Long todayVisits = todayVisit != null ? todayVisit.getVisitCount() : 0L;
        
        // 构建响应
        DailyVisitResp response = new DailyVisitResp();
        response.setTotalVisits(totalVisits);
        response.setTodayVisits(todayVisits);
        response.setVisitDate(today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        
        return response;
    }
}
