/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dto.BlogUser;
import java.util.List;

/**
 *
 * @author stephendowning
 */
public interface BlogUserService {
    
    public BlogUser getBlogUserById(int blogUserId);
    public List<BlogUser> getAllBlogUsers();
    public List<BlogUser> getActiveBlogUsers();
    public BlogUser addBlogUser (BlogUser user);
    public void editBlogUser (BlogUser user);
    public void deleteBlogUser (int userId);
    
}
