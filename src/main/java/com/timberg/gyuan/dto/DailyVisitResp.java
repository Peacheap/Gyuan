package com.timberg.gyuan.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "DailyVisitResp", description = "访问量统计响应")
public class DailyVisitResp {
    @Schema(description = "总访问量", example = "1234")
    private Long totalVisits;
    
    @Schema(description = "今日访问量", example = "56")
    private Long todayVisits;
    
    @Schema(description = "统计日期", example = "2025-01-23")
    private String visitDate;
}
