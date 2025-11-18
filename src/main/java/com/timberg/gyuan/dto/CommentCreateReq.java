package com.timberg.gyuan.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "CommentCreateReq", description = "创建评论请求")
public class CommentCreateReq {
    @Schema(description = "昵称", example = "Alice")
    private String nickname;

    @Schema(description = "邮箱，可选", example = "alice@example.com")
    private String email;

    @Schema(description = "评论内容", example = "这是一条留言")
    private String content;

    @Schema(description = "父留言ID，可选", example = "1")
    private Integer parentId;
}


