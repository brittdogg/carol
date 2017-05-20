/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dto;

import java.time.LocalDateTime;

/**
 *
 * @author trevor
 */
public class Comment {
    private int commentId;
    private int blogPostId;
    private String content;
    private BlogUser createdBy;
    private LocalDateTime createdDate;
    private BlogUser modifiedBy;
    private LocalDateTime modifiedDate;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(int blogPostId) {
        this.blogPostId = blogPostId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BlogUser createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public BlogUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(BlogUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
