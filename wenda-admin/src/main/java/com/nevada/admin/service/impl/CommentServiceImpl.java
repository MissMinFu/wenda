package com.nevada.admin.service.impl;

import com.nevada.admin.dao.CommentDao;
import com.nevada.admin.entity.Comment;
import com.nevada.admin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-03 14:35
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    //
    @Override
    public int addComment(Comment comment) {
        comment.setContext(comment.getContext());
        return commentDao.addComment(comment);
    }


    @Override
    public Comment getCommentsByEntity(int entityId) {
        return commentDao.selectCommentByEntity(entityId);
    }

    public int getCommentCount(int entityId,int entityType){
        return commentDao.getCommentCount(entityId,entityType);
    }
}
