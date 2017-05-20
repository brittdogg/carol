/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.CategoryDao;
import com.sg.carolsblog.dto.Category;
import java.util.List;

/**
 *
 * @author stephendowning
 */
public class CategoryServiceImpl implements CategoryService {
    
    CategoryDao catDao;
    
    public CategoryServiceImpl (CategoryDao catDao) {
        this.catDao = catDao;
    }

    @Override
    public Category getCategoryById(int categoryid) {
        return catDao.getCategoryById(categoryid);
   }

    @Override
    public List<Category> getAllCategories() {
        return catDao.getAllCategories();
   }

    @Override
    public Category addCategory(Category category) {
        return catDao.addCategory(category);
  }

    @Override
    public void editCategory(Category category) {
        catDao.editCategory(category);
   }

    @Override
    public void deleteCategory(int categoryId) {
        catDao.deleteCategory(categoryId);
   }    
}
