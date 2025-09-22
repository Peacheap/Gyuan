package com.timberg.gyuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timberg.gyuan.dto.TimCreateReq;
import com.timberg.gyuan.dto.TimResp;
import com.timberg.gyuan.model.Tim;
import com.timberg.gyuan.service.TimService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Tim Controller
 * 
 * @author Gyuan
 * @since 2025-01-27
 */
@Tag(name = "Tim信息管理", description = "Tim信息接口")
@RestController
@RequestMapping("/tim")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TimController {

    @Autowired
    private TimService timService;

    @Operation(summary = "添加Tim信息", description = "创建新的Tim信息记录")
    @PostMapping("/insert")
    public String insert(@RequestBody TimCreateReq createReq) {
        return timService.insert(createReq);
    }

    @Operation(summary = "查询Tim信息", description = "根据ID查询对应的Tim信息")
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(
            @Parameter(description = "Tim信息ID", example = "1") 
            @PathVariable Integer id) {
        TimResp resp = timService.findByIdAsResp(id);
        if (resp == null) {
            return ResponseEntity.status(404).body("未查询到此条数据");
        }
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "更新Tim信息", description = "更新指定ID的Tim信息")
    @PutMapping("/update")
    public String update(@RequestBody Tim tim) {
        return timService.update(tim);
    }

    @Operation(summary = "删除Tim信息", description = "删除指定ID的Tim信息")
    @DeleteMapping("/delete/{id}")
    public String delete(
            @Parameter(description = "Tim信息ID", example = "1") 
            @PathVariable Integer id) {
        return timService.delete(id);
    }
}