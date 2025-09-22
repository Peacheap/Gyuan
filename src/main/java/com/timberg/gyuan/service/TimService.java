package com.timberg.gyuan.service;

import com.timberg.gyuan.dto.TimCreateReq;
import com.timberg.gyuan.dto.TimResp;
import com.timberg.gyuan.model.Tim;

/**
 * Tim Service
 * 
 * @author Gyuan
 * @since 2025-01-27
 */
public interface TimService {

    /**
     * 新增信息
     *
     * @param createReq 创建请求
     * @return 操作结果
     */
    String insert(TimCreateReq createReq);

    /**
     * 查询id对应的信息
     *
     * @param id 信息ID
     * @return Tim实体
     */
    Tim findById(Integer id);

    /**
     * 查询id对应的信息并转换为响应DTO
     *
     * @param id 信息ID
     * @return TimResp响应对象
     */
    TimResp findByIdAsResp(Integer id);

    /**
     * 更新信息
     *
     * @param tim Tim实体
     * @return 操作结果
     */
    String update(Tim tim);

    /**
     * 删除id对应的信息
     *
     * @param id 信息ID
     * @return 操作结果
     */
    String delete(Integer id);
}