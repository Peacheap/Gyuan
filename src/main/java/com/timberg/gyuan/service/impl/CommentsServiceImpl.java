package com.timberg.gyuan.service.impl;

import com.timberg.gyuan.dto.CommentCreateReq;
import com.timberg.gyuan.dto.CommentResp;
import com.timberg.gyuan.mapper.CommentsMapper;
import com.timberg.gyuan.model.Comment;
import com.timberg.gyuan.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public int create(CommentCreateReq req, String ipAddress) {
        Comment c = new Comment();
        c.setNickname(req.getNickname());
        c.setEmail(req.getEmail());
        c.setContent(req.getContent());
        c.setParentId(req.getParentId());
        c.setIpAddress(ipAddress);
        return commentsMapper.insert(c);
    }

    @Override
    public List<CommentResp> listRoot(int page, int size) {
        int offset = (Math.max(page, 1) - 1) * Math.max(size, 1);
        List<Comment> list = commentsMapper.listRoot(offset, size);
        List<CommentResp> resp = new ArrayList<>();
        for (Comment c : list) {
            CommentResp r = new CommentResp();
            r.setId(c.getId());
            r.setNickname(c.getNickname());
            r.setEmail(c.getEmail());
            r.setContent(c.getContent());
            r.setLikes(c.getLikes());
            r.setParentId(c.getParentId());
            r.setCreatedAt(c.getCreatedAt());
            resp.add(r);
        }
        return resp;
    }

    @Override
    public List<CommentResp> listByParent(int parentId) {
        List<Comment> list = commentsMapper.listByParent(parentId);
        List<CommentResp> resp = new ArrayList<>();
        for (Comment c : list) {
            CommentResp r = new CommentResp();
            r.setId(c.getId());
            r.setNickname(c.getNickname());
            r.setEmail(c.getEmail());
            r.setContent(c.getContent());
            r.setLikes(c.getLikes());
            r.setParentId(c.getParentId());
            r.setCreatedAt(c.getCreatedAt());
            resp.add(r);
        }
        return resp;
    }

    @Override
    public int like(int id) {
        return commentsMapper.like(id);
    }
}


