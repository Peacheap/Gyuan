package com.timberg.gyuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timberg.gyuan.mapper.ResponseMapper;
import com.timberg.gyuan.service.ResponseService;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public String getRandomContent() {
        return responseMapper.selectRandomContent();
    }
}


