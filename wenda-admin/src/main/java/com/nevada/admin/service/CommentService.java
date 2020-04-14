package com.nevada.admin.service;

import com.nevada.admin.dao.CommentDao;
import com.nevada.admin.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:wenda
 * @description: 评论
 * @author: nevada
 * @create: 2020-04-03 14:18
 **/

public interface CommentService {


    int addComment(Comment comment);

    Comment getCommentsByEntity(int entityId);

    int getCommentCount(int entityId,int entityType);

}
