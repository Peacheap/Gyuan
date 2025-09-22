package com.timberg.gyuan.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tim {
    private Integer id;
    private String ip;
    private String province;
    private LocalDateTime time;
    private String str;
    private Long likes;
}
