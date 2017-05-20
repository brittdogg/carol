/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.CreateTestData;
import com.sg.carolsblog.dto.Category;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author stephendowning
 */
public class CategoryServiceTest {

    CategoryService catServ;

    public CategoryServiceTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        catServ = ctx.getBean("categoryService", CategoryService.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        CreateTestData.rebuildDatabase();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetCategory() {

        Category cat = new Category();

        cat.setCategoryName("apples");
        cat = catServ.addCategory(cat);
        Category fromDao = catServ.getCategoryById(cat.getCategoryId());

        assertTrue(cat.equals(fromDao));
        assertEquals(cat.getCategoryName(), fromDao.getCategoryName());
    }

    @Test
    public void testGetAllCategories() {

        Category cat = new Category();
        cat.setCategoryName("apples");
        cat = catServ.addCategory(cat);

        Category cat2 = new Category();
        cat2.setCategoryName("oranges");
        cat2 = catServ.addCategory(cat2);

        List<Category> catList = catServ.getAllCategories();
        assertEquals(catList.size(), 2);
    }

    @Test
    public void testEditCategory() {

        Category cat = new Category();
        cat.setCategoryName("apples");
        cat = catServ.addCategory(cat);

        cat.setCategoryName("oranges");
        catServ.editCategory(cat);

        Category fromDao = catServ.getCategoryById(cat.getCategoryId());

        assertTrue(cat.getCategoryName().equals(fromDao.getCategoryName()));
        assertEquals(cat.getCategoryId(), fromDao.getCategoryId());
    }

    @Test
    public void testDeleteCategoryById() {

        Category cat = new Category();
        cat.setCategoryName("apples");
        cat = catServ.addCategory(cat);

        Category cat2 = new Category();
        cat2.setCategoryName("oranges");
        cat2 = catServ.addCategory(cat2);

        catServ.deleteCategory(cat2.getCategoryId());
        List<Category> catList = catServ.getAllCategories();
        assertEquals(catList.size(), 1);
    }

}
