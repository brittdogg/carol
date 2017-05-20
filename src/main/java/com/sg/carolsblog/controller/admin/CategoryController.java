/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.controller.admin;

import com.sg.carolsblog.dao.CategoryDao;
import com.sg.carolsblog.dto.Category;
import com.sg.carolsblog.service.CategoryService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping({"/admin/category"})
public class CategoryController {
    
    CategoryDao catDao;
    CategoryService catServ;
    
    @Inject
    public CategoryController(CategoryDao catDao, CategoryService catServ) {
        this.catDao = catDao;
        this.catServ = catServ;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String showCategoryManagement (Model model) {
        List<Category> categoryList = catServ.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "manageCategories";
    }
    
    @RequestMapping(value="/addCategory", method = RequestMethod.POST)
    public String createCategory (HttpServletRequest request) {
        
        Category category = new Category();
        category.setCategoryName(request.getParameter("categoryName"));
        catServ.addCategory(category);
        return "redirect:/admin/category";
    }
    
    @RequestMapping(value="/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(HttpServletRequest request) {
        String categoryParameter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryParameter);
        catServ.deleteCategory(categoryId);
        return "redirect:/admin/category";
    }
    
    @RequestMapping(value= "/editCategory", method=RequestMethod.GET)
    public String displayEditCategoryForm(HttpServletRequest request, Model model) {
        String categoryIdParameter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParameter);
        Category category = catServ.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "editCategory";
    }
    
    @RequestMapping(value= "/editCategory", method = RequestMethod.POST)
    public String editCategory(@ModelAttribute("category") Category category) {
        catServ.editCategory(category);
        return "redirect:/admin/category";
    }
    
   
}
