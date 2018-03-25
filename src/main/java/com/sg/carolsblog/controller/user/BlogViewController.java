package com.sg.carolsblog.controller.user;

import com.sg.carolsblog.dto.BlogPost;
import com.sg.carolsblog.dto.BlogUser;
import com.sg.carolsblog.dto.Comment;
import com.sg.carolsblog.service.BlogService;
import com.sg.carolsblog.service.BlogUserService;
import com.sg.carolsblog.service.CategoryService;
import com.sg.carolsblog.service.CommentService;
import com.sg.carolsblog.service.StaticPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping({"/blog"})
public class BlogViewController {

    BlogService blogService;
    CategoryService categoryService;
    StaticPageService pageService;
    BlogUserService userService;
    CommentService comService;

    public BlogViewController(
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
    public String doBlogStuff() {
        return "redirect:/";
    }

    @RequestMapping(value = "/showPost/{postId}", method = RequestMethod.GET)
    public String showPost(Model model, @PathVariable int postId) {
        
        model = getStuffForAllPages(model);
        BlogPost postToDisplay = blogService.getPostById(postId);
        List<Comment> commentsToDisplay = comService.getCommentsByPost(postId);
        if (postToDisplay == null) {
            return "redirect:/blog";
        } else {
            model.addAttribute("post", postToDisplay);
            model.addAttribute("postComments", commentsToDisplay);
            return "showPost";
        }
    }
    
    @RequestMapping(value="/category/{categoryId}", method=RequestMethod.GET)
    public String showPostsByCategory(Model model, @PathVariable int categoryId) {
        model = getStuffForAllPages(model);
        model.addAttribute("posts", blogService.getPostsByCategory(categoryId));
        model.addAttribute("category", categoryService.getCategoryById(categoryId));
        return "showPostsByCategory";
    }
    
    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public String showPostsByAuthor(Model model, @PathVariable int userId) {
        model = getStuffForAllPages(model);
        model.addAttribute("posts", blogService.getPostsByUser(userId));
        model.addAttribute("user", userService.getBlogUserById(userId));
        return "showPostsByUser";
    }
    
        @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(HttpServletRequest request) {

        Comment comment = new Comment();

        String postId = (request.getParameter("postId"));
        int BPId = Integer.parseInt(postId);
        comment.setBlogPostId(BPId);
        comment.setContent(request.getParameter("content"));

        BlogUser bUser = new BlogUser();

        String BlogUserId = (request.getParameter("createdBy.blogUserId"));
        int BuId = Integer.parseInt(BlogUserId);
        bUser.setBlogUserId(BuId);
        comment.setCreatedBy(bUser);

        BlogUser bUser2 = new BlogUser();

        String BUserId = (request.getParameter("modifiedBy.blogUserId"));
        int BuId2 = Integer.parseInt(BUserId);
        bUser2.setBlogUserId(BuId2);
        comment.setModifiedBy(bUser2);

        comService.addComment(comment);

        return "redirect:/blog/showPost/" + postId;

    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public String deleteComment(HttpServletRequest request) {
        String commentParameter = request.getParameter("commentId");
        int commentId = Integer.parseInt(commentParameter);
        String postId = (request.getParameter("postId"));

        comService.deleteComment(commentId);
        
        return "redirect:/blog/showPost/" + postId;
    }
    
    @RequestMapping(value="/tag/{tagName}", method=RequestMethod.GET)
    public String showPostsForTagName(Model model, @PathVariable String tagName) {
        List<BlogPost> posts = blogService.getPostsByTag(tagName);
        model = getStuffForAllPages(model);
        model.addAttribute("posts", posts);
        model.addAttribute("tagName", tagName);
        return "showTagPosts";
    }
    
    private Model getStuffForAllPages(Model model) {
        model.addAttribute("staticPages", pageService.getAllPages());
        // TODO add logged-in user information here
        // add nav bar active information here
        return model;
    }
}
