/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.controller.user;

import com.sg.carolsblog.dto.StaticPage;
import com.sg.carolsblog.service.StaticPageService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author trevor
 */
@Controller
@RequestMapping(value = "/static")
public class PageViewController {

    private StaticPageService service;

    @Inject
    public PageViewController(StaticPageService pageService) {
        this.service = pageService;
    }

    @RequestMapping(value = "displayPage/{slug}", method = RequestMethod.GET)
    public String showStaticPage(Model model,
            @PathVariable String slug) {
        model = getStuffForAllPages(model);
        model.addAttribute("slug", slug);
        return "showPage";
    }

    @RequestMapping(value = "showPage/{pageId}", method = RequestMethod.GET)
    public String showStaticPage(Model model,
            @PathVariable int pageId) {
        model = getStuffForAllPages(model);
        StaticPage staticPage = service.getPageById(pageId);
        model.addAttribute("page", staticPage);
        return "showPage";
    }

    private Model getStuffForAllPages(Model model) {
        model.addAttribute("staticPages", service.getAllPages());
        // TODO add logged-in user information here
        // add nav bar active information here
        return model;
    }
}