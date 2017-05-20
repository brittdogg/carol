/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.controller.admin;

import com.sg.carolsblog.dao.BlogUserDao;
import com.sg.carolsblog.dto.BlogUser;
import com.sg.carolsblog.service.BlogUserService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author stephendowning
 */
@Controller
@RequestMapping({"/admin/user"})
public class UserController {

    BlogUserDao BuDao;
    BlogUserService BuServ;
    PasswordEncoder encoder;

    @Inject
    public UserController(
            BlogUserDao BuDao,
            BlogUserService BuServ,
            PasswordEncoder encoder) {
        this.BuDao = BuDao;
        this.BuServ = BuServ;
        this.encoder = encoder;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showUserManagement(Model model) {
        // only active blog users will be shown for now.
        List<BlogUser> blogUserList = BuServ.getActiveBlogUsers();
        model.addAttribute("blogUserList", blogUserList);
        return "manageUsers";

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addBlogUser(HttpServletRequest request) {

        String clearText = request.getParameter("password");
        String hashedPassword = encoder.encode(clearText);
        
        BlogUser blogUser = new BlogUser();
        blogUser.setDisplayName(request.getParameter("displayName"));
        blogUser.setLoginName(request.getParameter("loginName"));
        blogUser.setPassword(hashedPassword);
//        blogUser.setRole(request.getParameter("role"));

        BuServ.addBlogUser(blogUser);
        return "redirect:/admin/user";

    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteBlogUser(HttpServletRequest request) {
        String blogUserIdParameter = request.getParameter("blogUserId");
        int blogUserId = Integer.parseInt(blogUserIdParameter);
        BuServ.deleteBlogUser(blogUserId);
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public String displayEditBlogUserForm(HttpServletRequest request, Model model) {
        String blogUserIdParameter = request.getParameter("blogUserId");
        int blogUserId = Integer.parseInt(blogUserIdParameter);
        BlogUser blogUser = BuServ.getBlogUserById(blogUserId);
        model.addAttribute("blogUser", blogUser);
        return "editUser";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editBlogUser(@ModelAttribute("blogUser") BlogUser blogUser) {
        BuServ.editBlogUser(blogUser);
        return "redirect:/admin/user";
    }

}
