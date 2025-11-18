package com.timberg.gyuan.service;

import com.timberg.gyuan.dto.DailyVisitResp;

public interface DailyVisitService {
    
    /**
     * 增加访问量
     */
    void incrementVisit();
    
    /**
     * 获取访问量统计
     */
    DailyVisitResp getVisitStats();
}
