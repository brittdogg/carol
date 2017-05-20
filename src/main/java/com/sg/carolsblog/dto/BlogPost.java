/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author trevor
 */
public class BlogPost {

    private int blogPostId;
    
    @NotEmpty
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime createdDate;

    @NotEmpty(message = "Please enter content for the post.")
    private String content;
    
    @NotEmpty(message = "Please enter a title.")
    @Length(max = 50, message = "Title must be less than 50 characters.")
    private String title;
    
    @NotEmpty(message = "Please enter a date to publish the post.")
    private LocalDateTime publishDate;
    
    private LocalDateTime expirationDate;
    
    @NotEmpty
    private LocalDateTime modifiedDate;
    private boolean approved;
    
    @NotEmpty (message = "Please enter a category.")
    private Category category;
    
    @NotEmpty
    private BlogUser createdBy;
    
    @NotEmpty
    private BlogUser modifiedBy;
    
    @NotEmpty(message = "Please enter an alias for the URL.")
    private String urlAlias;

    public int getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(int blogPostId) {
        this.blogPostId = blogPostId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BlogUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BlogUser createdBy) {
        this.createdBy = createdBy;
    }

    public BlogUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(BlogUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getUrlAlias() {
        return urlAlias;
    }

    public void setUrlAlias(String urlAlias) {
        this.urlAlias = urlAlias;
    }
}
