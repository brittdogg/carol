/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dto.Comment;
import java.util.List;

/**
 *
 * @author stephendowning
 */
public interface CommentService {
    
    public Comment getCommentById(int id);
    public List<Comment> getAllComments();
    public List<Comment> getCommentsByPost(int postId);
    public Comment addComment(Comment comment);
    public void editComment(Comment comment);
    public void deleteComment(int commentId);
    
}
