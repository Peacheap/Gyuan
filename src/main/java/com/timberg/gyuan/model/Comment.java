package com.timberg.gyuan.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private String nickname;
    private String content;
    private String ipAddress;
    private String email;
    private Integer parentId;
    private Integer likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


