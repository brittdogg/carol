/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.CommentDao;
import com.sg.carolsblog.dto.Comment;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author stephendowning
 */
public class CommentServiceImpl implements CommentService {
    
    CommentDao comDao;
    
    public CommentServiceImpl (CommentDao comDao) {
        this.comDao = comDao;
    }

    @Override
    public Comment getCommentById(int id) {
        return comDao.getCommentById(id);
   }

    @Override
    public List<Comment> getAllComments() {
        return comDao.getAllComments();
   }

    @Override
    public List<Comment> getCommentsByPost(int postId) {
        return comDao.getCommentsByPost(postId);
   }

    @Override
    public Comment addComment(Comment comment) {
        
        LocalDateTime createdDate = LocalDateTime.now();
        comment.setCreatedDate(createdDate);
        comment.setModifiedDate(createdDate);

        return comDao.addComment(comment);
   }

    @Override
    public void editComment(Comment comment) {
        comDao.editComment(comment);
   }

    @Override
    public void deleteComment(int commentId) {
        comDao.deleteComment(commentId);
   }
    
}
