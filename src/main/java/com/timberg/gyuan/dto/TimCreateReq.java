package com.timberg.gyuan.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "TimCreateReq", description = "创建 Tim 的请求体")
public class TimCreateReq {
    @Schema(description = "发送者 IP 地址", example = "127.0.0.1")
    @NotBlank
    @Size(max = 255)
    private String ip;

    @Schema(description = "发送者居住省份", example = "辽宁")
    @NotBlank
    @Size(max = 255)
    private String province;

    @Schema(description = "信息时间", example = "2025-09-19 16:58:00")
    @NotNull
    private LocalDateTime time;

    @Schema(description = "信息内容", example = "你好。")
    @NotBlank
    @Size(max = 255)
    private String str;

    @Schema(description = "点赞数", example = "52")
    @NotNull
    @Min(0)
    @Max(4294967295L) // 对应 MySQL unsigned int 上限
    private Long likes;
}

