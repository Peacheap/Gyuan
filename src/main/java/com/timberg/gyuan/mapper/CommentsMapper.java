package com.timberg.gyuan.mapper;

import com.timberg.gyuan.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentsMapper {
    int insert(Comment comment);

    List<Comment> listRoot(@Param("offset") int offset, @Param("limit") int limit);

    int countRoot();

    List<Comment> listByParent(@Param("parentId") int parentId);

    int like(@Param("id") int id);
}


