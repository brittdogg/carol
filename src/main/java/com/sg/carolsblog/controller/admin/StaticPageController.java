/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.controller.admin;

import com.sg.carolsblog.dto.BlogUser;
import com.sg.carolsblog.dto.StaticPage;
import com.sg.carolsblog.service.StaticPageService;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author trevor
 */
@Controller
@RequestMapping(value = "/admin/static")
public class StaticPageController {

    private StaticPageService service;

    @Inject
    public StaticPageController(StaticPageService pageService) {
        this.service = pageService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showPageManagement(Model model) {
        List<StaticPage> pages = service.getAllPages();
        model.addAttribute("pages", pages);
        return "manageStaticPages";
    }

    @RequestMapping(value = "edit/{pageId}", method = RequestMethod.GET)
    public String displayEditPageForm(Model model,
            @PathVariable int pageId) {

        // Get the page to be edited
        StaticPage staticPage = service.getPageById(pageId);

        // Add it to the model so it can be accessed by spring forms
        model.addAttribute("staticPage", staticPage);

        return "editPage";
    }

    @RequestMapping(value = "edit/{pageId}", method = RequestMethod.POST)
    public String editPage(Model model,
            @ModelAttribute StaticPage staticPage,
            BindingResult result,
            @PathVariable int pageId) {

        // Edit the page
        service.editPage(staticPage);
        return "redirect:/admin/static";
    }

    @RequestMapping(value = "delete/{pageId}", method = RequestMethod.GET)
    public String deletePage(
            @PathVariable int pageId) {

        // Edit the page
        service.deletePage(pageId);
        return "redirect:/admin/static";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addStaticPage(Model model) {
        StaticPage page = new StaticPage();
        model.addAttribute("staticPage", page);
        return "addPage";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addStaticPage(HttpServletRequest request,
            @ModelAttribute StaticPage staticPage,
            @RequestParam int userId,
            BindingResult result) {

        // Set url Alias
        // Set the times
        LocalDateTime now = LocalDateTime.now();
        staticPage.setCreatedDate(now);
        staticPage.setModifiedDate(now);
        // Set the use id to fill in the foreign key in static page
        BlogUser activeUser = new BlogUser();
        activeUser.setBlogUserId(userId);
        // Set page user
        staticPage.setCreatedBy(activeUser);
        staticPage.setModifiedBy(activeUser);
        // Add the page to the database
        staticPage = service.addPage(staticPage);

        return "redirect:/static/showPage/" + staticPage.getStaticPageId();
    }

    private Model getStuffForAllPages(Model model) {
        model.addAttribute("staticPages", service.getAllPages());
        // TODO add logged-in user information here
        // add nav bar active information here
        return model;
    }
}
