package com.sg.carolsblog.controller.user;

import com.sg.carolsblog.service.BlogService;
import com.sg.carolsblog.service.StaticPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/"})
public class HomeController {
    
    BlogService blogService;
    StaticPageService pageService;
        
    public HomeController(
            BlogService blogService,
            StaticPageService pageService) {
        this.blogService = blogService;
        this.pageService = pageService;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String showHomePage(Model model) {
        
        model.addAttribute("blogPosts", blogService.getAllPosts());
        model.addAttribute("staticPages", pageService.getAllPages());
        return "index";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
    
    
}
