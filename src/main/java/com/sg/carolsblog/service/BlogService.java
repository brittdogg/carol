/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dto.BlogPost;
import java.util.List;

/**
 *
 * @author bdogg
 */
public interface BlogService {

    public BlogPost getPostById(int postId);

    public List<BlogPost> getAllPosts();

    public List<BlogPost> getPostsByTag(String tagName);

    public List<BlogPost> getPostsByCategory(int categoryId);

    public List<BlogPost> getPostsByUser(int userId);

    public BlogPost addPost(BlogPost post);

    public void editPost(BlogPost post);

    public void deletePost(int postId);

    public void approvePost(int postId);

}
