package com.timberg.gyuan.controller;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Root endpoint for basic info/health check
 */
@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<?> root() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("app", "gyuan-backend");
        info.put("status", "ok");
        info.put("time", Instant.now().toString());
        info.put("endpoints", List.of(
                "/tim/findById/{id}",
                "/tim/insert",
                "/tim/update",
                "/tim/delete/{id}"
        ));
        return ResponseEntity.ok(info);
    }
}

