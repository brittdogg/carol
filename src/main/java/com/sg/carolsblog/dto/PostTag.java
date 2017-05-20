/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dto;

/**
 *
 * @author trevor
 */
public class PostTag {

    private int postTagId;
    private Tag tag;
    private BlogPost blogPost;
    private StaticPage staticPage;

    public int getPostTagId() {
        return postTagId;
    }

    public void setPostTagId(int postTagId) {
        this.postTagId = postTagId;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public StaticPage getStaticPage() {
        return staticPage;
    }

    public void setStaticPage(StaticPage staticPage) {
        this.staticPage = staticPage;
    }
    
}
