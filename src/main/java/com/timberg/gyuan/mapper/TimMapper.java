package com.timberg.gyuan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.timberg.gyuan.dto.TimCreateReq;
import com.timberg.gyuan.model.Tim;

@Mapper
public interface TimMapper {

    int insert(TimCreateReq createReq);

    Tim findById(@Param("id") Integer id);

    int update(@Param("tim") Tim tim);

    int delete(@Param("id") Integer id);
}

