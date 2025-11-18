package com.timberg.gyuan.controller;

import com.timberg.gyuan.dto.CommentCreateReq;
import com.timberg.gyuan.dto.CommentResp;
import com.timberg.gyuan.service.CommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Tag(name = "留言板", description = "评论创建、查询与点赞")
@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Operation(summary = "创建评论或回复")
    @PostMapping
    public String create(@RequestBody CommentCreateReq req, HttpServletRequest request) {
        String ip = extractClientIp(request);
        commentsService.create(req, ip);
        return "ok";
    }

    @Operation(summary = "分页获取根评论")
    @GetMapping
    public List<CommentResp> listRoot(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return commentsService.listRoot(page, size);
    }

    @Operation(summary = "获取某条评论的回复列表")
    @GetMapping("/{parentId}/replies")
    public List<CommentResp> listByParent(@PathVariable int parentId) {
        return commentsService.listByParent(parentId);
    }

    @Operation(summary = "点赞")
    @PostMapping("/{id}/like")
    public String like(@PathVariable int id) {
        commentsService.like(id);
        return "ok";
    }

    private String extractClientIp(HttpServletRequest request) {
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isEmpty()) {
            int comma = xff.indexOf(',');
            return comma > 0 ? xff.substring(0, comma).trim() : xff.trim();
        }
        String realIp = request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isEmpty()) {
            return realIp;
        }
        return request.getRemoteAddr();
    }
}


