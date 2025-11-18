package com.timberg.gyuan.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyVisit {
    private Integer id;
    private LocalDate visitDate;
    private Long visitCount;
    private LocalDateTime lastUpdate;
}
