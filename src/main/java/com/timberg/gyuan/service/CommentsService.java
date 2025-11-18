package com.timberg.gyuan.service;

import com.timberg.gyuan.dto.CommentCreateReq;
import com.timberg.gyuan.dto.CommentResp;

import java.util.List;

public interface CommentsService {
    int create(CommentCreateReq req, String ipAddress);

    List<CommentResp> listRoot(int page, int size);

    List<CommentResp> listByParent(int parentId);

    int like(int id);
}


