package com.sg.carolsblog.controller.admin;

import com.sg.carolsblog.dto.BlogPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sg.carolsblog.service.BlogService;
import com.sg.carolsblog.service.BlogUserService;
import com.sg.carolsblog.service.CategoryService;
import com.sg.carolsblog.service.CommentService;
import com.sg.carolsblog.service.StaticPageService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping({"/admin/posts"})
public class BlogController {

    BlogService blogService;
    CategoryService categoryService;
    StaticPageService pageService;
    BlogUserService userService;
    CommentService comService;

    public BlogController(
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

    @RequestMapping(method = RequestMethod.GET)
    public String showPostManagement(Model model) {
        model.addAttribute("posts", blogService.getAllPosts());
        return "managePosts";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.GET)
    public String showCreatePostForm(Model model,
            @ModelAttribute BlogPost blogPost) {

        model = getStuffForAllPages(model);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "addPost";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(Model model,
            @ModelAttribute BlogPost blogPost, BindingResult result) {
        BlogPost post = blogService.addPost(blogPost);
        return "redirect:/blog/showPost/" + post.getBlogPostId();
    }

    @RequestMapping(value = "editPost/{postId}", method = RequestMethod.GET)
    public String showEditPostForm(Model model, @PathVariable int postId) {

        model = getStuffForAllPages(model);
        // Get the post to be edited
        BlogPost blogPost = blogService.getPostById(postId);
        if (blogPost == null) {
            return "redirect:/blog"; //TODO error handling for trying to view deleted posts
        } else {
            // Add it to the model so it can be accessed by spring forms
            model.addAttribute("blogPost", blogPost);
            model.addAttribute("categories", categoryService.getAllCategories());
        }

        return "editPost";
    }

    @RequestMapping(value = "/editPost/{postId}", method = RequestMethod.POST)
    public String editPost(Model model, @PathVariable int postId,
            @ModelAttribute BlogPost blogPost) {
        blogService.editPost(blogPost);
        return "redirect:/blog/showPost/" + postId;
    }

    @RequestMapping(value = "/deletePost/{postId}", method = RequestMethod.POST)
    public String deletePost(Model model, @PathVariable int postId) {
        blogService.deletePost(postId);
        //TODO should redirect to different places based on origin of request
        return "redirect:/admin/posts";
    }

    private Model getStuffForAllPages(Model model) {
        model.addAttribute("staticPages", pageService.getAllPages());
        // TODO add logged-in user information here
        // add nav bar active information here
        return model;
    }
    

}
