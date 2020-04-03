package com.nevada.admin.entity;


public class Comment {
    private int id;
    private String context;
    private int userId;
    private int entityId;
    private int entityType;
    private int createdDate;
    private int status;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public int getEntityId() {
        return entityId;
    }
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    public int getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(int createdDate) {
        this.createdDate = createdDate;
    }
    public int getEntityType() {
        return entityType;
    }
    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
