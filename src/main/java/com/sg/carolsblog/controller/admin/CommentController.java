/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.controller.admin;

import com.sg.carolsblog.dto.Category;
import com.sg.carolsblog.dto.Comment;
import com.sg.carolsblog.service.BlogService;
import com.sg.carolsblog.service.BlogUserService;
import com.sg.carolsblog.service.CategoryService;
import com.sg.carolsblog.service.CommentService;
import com.sg.carolsblog.service.StaticPageService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author stephendowning
 */
@Controller
@RequestMapping({"/admin/comment"})
public class CommentController {
    
    BlogService blogService;
    CategoryService categoryService;
    StaticPageService pageService;
    BlogUserService userService;
    CommentService comService;

    public CommentController(
            BlogService blogService,
            CategoryService categoryService,
            StaticPageService pageService,
            BlogUserService userService,
            CommentService comService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
        this.pageService = pageService;
        this.userService = userService;
        this.comService = comService;
    
}
    
    @RequestMapping (method=RequestMethod.GET)
    public String showCommentManagement (Model model) {
        List<Comment> commentList = comService.getAllComments();
        model.addAttribute("commentList", commentList);
        return "manageComments";
    }
    
    @RequestMapping (value="/deleteComment", method=RequestMethod.GET)
    public String deleteComment(HttpServletRequest request) {
        String commentParameter = request.getParameter("commentId");
        int commentId = Integer.parseInt(commentParameter);
        comService.deleteComment(commentId);
        
        return "redirect:/admin/comment";
    }
    
}
