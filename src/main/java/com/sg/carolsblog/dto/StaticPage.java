/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author trevor
 */
public class StaticPage {
    private int staticPageId;
    private String urlAlias;
    private String title;
    private String content;
    private int pageOrder;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private BlogUser createdBy;
    private BlogUser modifiedBy;

    public int getStaticPageId() {
        return staticPageId;
    }

    public void setStaticPageId(int staticPageId) {
        this.staticPageId = staticPageId;
    }

    public String getUrlAlias() {
        return urlAlias;
    }

    public void setUrlAlias(String urlAlias) {
        this.urlAlias = urlAlias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(int pageOrder) {
        this.pageOrder = pageOrder;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
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
}
