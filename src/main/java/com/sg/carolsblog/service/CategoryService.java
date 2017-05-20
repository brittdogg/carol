/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dto.Category;
import java.util.List;

/**
 *
 * @author stephendowning
 */
public interface CategoryService {
    
    public Category getCategoryById(int id);
    public List<Category> getAllCategories();
    public Category addCategory(Category category);
    public void editCategory(Category category);
    public void deleteCategory(int categoryId);
    
}
