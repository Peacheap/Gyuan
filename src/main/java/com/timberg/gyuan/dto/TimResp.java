package com.timberg.gyuan.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TimResp", description = "Tim 返回数据")
public class TimResp {
    @Schema(description = "信息序号", example = "1")
    private Integer id;

    @Schema(description = "发送者 IP 地址", example = "127.0.0.1")
    private String ip;

    @Schema(description = "发送者居住省份", example = "辽宁")
    private String province;

    @Schema(description = "信息时间", example = "2025-09-19 16:58:00")
    private LocalDateTime time;

    @Schema(description = "信息内容", example = "你好。")
    private String str;

    @Schema(description = "点赞数", example = "52")
    private Long likes;
}

