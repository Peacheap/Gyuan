package com.timberg.gyuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timberg.gyuan.dto.TimCreateReq;
import com.timberg.gyuan.dto.TimResp;
import com.timberg.gyuan.mapper.TimMapper;
import com.timberg.gyuan.model.Tim;
import com.timberg.gyuan.service.TimService;

/**
 * Tim ServiceImpl
 * 
 * @author Gyuan
 * @since 2025-01-27
 */
@Service
public class TimServiceImpl implements TimService {

    @Autowired
    private TimMapper timMapper;

    @Override
    public String insert(TimCreateReq createReq) {
        int result = timMapper.insert(createReq);
        return result > 0 ? "succeed" : "failed";
    }

    @Override
    public Tim findById(Integer id) {
        return timMapper.findById(id);
    }

    @Override
    public TimResp findByIdAsResp(Integer id) {
        Tim tim = findById(id);
        if (tim == null) {
            return null;
        }
        return convertToResp(tim);
    }

    @Override
    public String update(Tim tim) {
        int result = timMapper.update(tim);
        return result > 0 ? "succeed" : "failed";
    }

    @Override
    public String delete(Integer id) {
        int result = timMapper.delete(id);
        return result > 0 ? "succeed" : "failed";
    }

    /**
     * 将Tim实体转换为TimResp DTO
     *
     * @param tim Tim实体
     * @return TimResp响应对象
     */
    private TimResp convertToResp(Tim tim) {
        if (tim == null) {
            return null;
        }
        
        TimResp resp = new TimResp();
        resp.setId(tim.getId());
        resp.setIp(tim.getIp());
        resp.setProvince(tim.getProvince());
        resp.setTime(tim.getTime());
        resp.setStr(tim.getStr());
        resp.setLikes(tim.getLikes());
        
        return resp;
    }
}