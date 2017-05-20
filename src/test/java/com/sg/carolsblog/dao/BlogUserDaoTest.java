///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.carolsblog.dao;
//
//import com.sg.carolsblog.dto.BlogUser;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author stephendowning
// */
//public class BlogUserDaoTest {
//
//    BlogUserDao BuDao;
//
//    public BlogUserDaoTest() {
//        ApplicationContext ctx
//                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        BuDao = ctx.getBean("blogUserDao", BlogUserDao.class);
//
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
////        CreateTestData.testTearDown();
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//
////        List<BlogUser> users = BuDao.getAllBlogUsers();
////        for (BlogUser currentUser : users) {
////            BuDao.deleteBlogUser(currentUser.getBlogUserId());
////        }
//        CreateTestData.rebuildDatabase();
//
//    }
//
//    @After
//    public void tearDown() {
//
////        List<BlogUser> users = BuDao.getAllBlogUsers();
////        for (BlogUser currentUser : users) {
////            BuDao.deleteBlogUser(currentUser.getBlogUserId());
////        }
//    }
//
//    @Test
//    public void testDeleteBlogUserById() {
//    }
//
//    @Test
//    public void testGetAllBlogUsers() {
//
//        BlogUser chuck = new BlogUser();
//        chuck.setDisplayName("chuck");
//        chuck.setLoginName("chaz");
//        chuck.setPassword("cDaddy");
//        chuck = BuDao.addBlogUser(chuck);
//
//        BlogUser hank = new BlogUser();
//        hank.setDisplayName("hank");
//        hank.setLoginName("hardon");
//        hank.setPassword("Hdaddy");
//        hank = BuDao.addBlogUser(hank);
//
//        List<BlogUser> userList = BuDao.getAllBlogUsers();
//        assertEquals(userList.size(), 2);
//
//    }
//
//    @Test
//    public void testAddGetBlogUser() {
//        BlogUser chuck = new BlogUser();
//        chuck.setDisplayName("chuck");
//        chuck.setLoginName("chaz");
//        chuck.setPassword("cDaddy");
//        chuck = BuDao.addBlogUser(chuck);
//
//        BlogUser fromDao = BuDao.getBlogUserById(chuck.getBlogUserId());
//
//        assertEquals(chuck.getDisplayName(), fromDao.getDisplayName());
//        assertEquals(chuck.getLoginName(), fromDao.getLoginName());
//        assertEquals(chuck.getPassword(), fromDao.getPassword());
//
//        assertEquals(chuck.getBlogUserId(), fromDao.getBlogUserId());
//
//    }
//
//    @Test
//    public void testEditBlogUser() {
//
//        BlogUser chuck = new BlogUser();
//        chuck.setDisplayName("chuck");
//        chuck.setLoginName("chaz");
//        chuck.setPassword("cDaddy");
//        chuck = BuDao.addBlogUser(chuck);
//        
//        chuck.setLoginName("theDude");
//        BuDao.editBlogUser(chuck);
//
//        BlogUser fromDao = BuDao.getBlogUserById(chuck.getBlogUserId());
//        assertEquals(chuck.getLoginName(), fromDao.getLoginName());
//
//    }
//
//}
