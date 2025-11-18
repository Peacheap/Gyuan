package com.timberg.gyuan.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@Schema(name = "CommentResp", description = "评论返回对象")
public class CommentResp {
    private Integer id;
    private String nickname;
    private String content;
    private String email;
    private Integer parentId;
    private Integer likes;
    private LocalDateTime createdAt;
}


