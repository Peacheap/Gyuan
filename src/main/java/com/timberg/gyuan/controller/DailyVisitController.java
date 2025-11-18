package com.timberg.gyuan.controller;

import com.timberg.gyuan.dto.DailyVisitResp;
import com.timberg.gyuan.service.DailyVisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/daily-visit")
@Tag(name = "访问量统计")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DailyVisitController {

    @Autowired
    private DailyVisitService dailyVisitService;

    @PostMapping("/increment")
    @Operation(summary = "增加访问量")
    public String incrementVisit() {
        dailyVisitService.incrementVisit();
        return "访问量已增加";
    }

    @GetMapping("/stats")
    @Operation(summary = "获取访问量统计")
    public DailyVisitResp getVisitStats() {
        return dailyVisitService.getVisitStats();
    }
}
