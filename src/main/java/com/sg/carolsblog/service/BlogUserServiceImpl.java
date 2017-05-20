/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.BlogUserDao;
import com.sg.carolsblog.dto.BlogUser;
import java.util.List;

/**
 *
 * @author stephendowning
 */
public class BlogUserServiceImpl implements BlogUserService {
    
    BlogUserDao BuDao;
    
    public BlogUserServiceImpl (BlogUserDao BuDao){
        this.BuDao = BuDao;
    }

    @Override
    public BlogUser getBlogUserById(int blogUserId) {
        return BuDao.getBlogUserById(blogUserId);
    }

    @Override
    public List<BlogUser> getAllBlogUsers() {
        return BuDao.getAllBlogUsers();
   }
    
    @Override
    public List<BlogUser> getActiveBlogUsers() {
        return BuDao.getActiveBlogUsers();
    }

    @Override
    public BlogUser addBlogUser(BlogUser user) {
        return BuDao.addBlogUser(user);
   }

    @Override
    public void editBlogUser(BlogUser user) {
        BuDao.editBlogUser(user);
   }

    @Override
    public void deleteBlogUser(int userId) {
        BuDao.deleteBlogUser(userId);
   }
    
}
