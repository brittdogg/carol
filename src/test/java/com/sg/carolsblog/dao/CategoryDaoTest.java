///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.carolsblog.dao;
//
//import com.sg.carolsblog.dto.Category;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author stephendowning
// */
//public class CategoryDaoTest {
//
//    CategoryDao catDao;
//    CreateTestData createTestData = new CreateTestData();
//
//    public CategoryDaoTest() {
//        ApplicationContext ctx
//                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//        catDao = ctx.getBean("categoryDao", CategoryDao.class);
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//
//        CreateTestData.rebuildDatabase();
//
//    }
//
//    @After
//    public void tearDown() {
////      CreateTestData.testTearDown();
//    }
//
//    @Test
//    public void testDeleteCategoryById() {
//
//        Category cat = new Category();
//        cat.setCategoryName("apples");
//        cat = catDao.addCategory(cat);
//
//        Category cat2 = new Category();
//        cat2.setCategoryName("oranges");
//        cat2 = catDao.addCategory(cat2);
//
//        catDao.deleteCategory(cat2.getCategoryId());
//        List<Category> catList = catDao.getAllCategories();
//        assertEquals(catList.size(), 1);
//    }
//
//    @Test
//    public void testGetAllCategories() {
//        Category cat = new Category();
//        cat.setCategoryName("apples");
//        cat = catDao.addCategory(cat);
//
//        Category cat2 = new Category();
//        cat2.setCategoryName("oranges");
//        cat2 = catDao.addCategory(cat2);
//
//        List<Category> catList = catDao.getAllCategories();
//        assertEquals(catList.size(), 2);
//
//    }
//
//    @Test
//    public void testAddGetCategory() {
//
//        Category cat = new Category();
//
//        cat.setCategoryName("apples");
//        cat = catDao.addCategory(cat);
//        Category fromDao = catDao.getCategoryById(cat.getCategoryId());
//
//        assertTrue(cat.equals(fromDao));
//        assertEquals(cat.getCategoryName(), fromDao.getCategoryName());
//    }
//
//    @Test
//    public void testEditCategory() {
//
//        Category cat = new Category();
//        cat.setCategoryName("apples");
//        cat = catDao.addCategory(cat);
//
//        cat.setCategoryName("oranges");
//        catDao.editCategory(cat);
//
//        Category fromDao = catDao.getCategoryById(cat.getCategoryId());
//
//        assertTrue(cat.getCategoryName().equals(fromDao.getCategoryName()));
//        assertEquals(cat.getCategoryId(), fromDao.getCategoryId());
//
//    }
//
//}
