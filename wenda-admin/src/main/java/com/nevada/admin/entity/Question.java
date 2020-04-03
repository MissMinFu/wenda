package com.nevada.admin.entity;

import java.util.Date;

/**
 * @program:wenda
 * @description: 问题实体
 * @author: nevada
 * @create: 2020-04-03 14:53
 **/
public class Question {
    private int id;
    private String title;
    private String context;
    private int userId;
    private Date createdDate;
    private int commentCount;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
