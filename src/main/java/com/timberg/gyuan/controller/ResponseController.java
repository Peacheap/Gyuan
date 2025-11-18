package com.timberg.gyuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timberg.gyuan.service.ResponseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "随机响应", description = "从 response 表随机返回一条 content")
@RestController
@RequestMapping("/response")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @Operation(summary = "随机 content")
    @GetMapping("/getResponse")
    public ResponseEntity<?> random() {
        String content = responseService.getRandomContent();
        if (content == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(content);
    }
}


