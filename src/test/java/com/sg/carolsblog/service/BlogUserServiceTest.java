/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.CreateTestData;
import com.sg.carolsblog.dto.BlogUser;
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
public class BlogUserServiceTest {

    BlogUserService BuServ;

    public BlogUserServiceTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        BuServ = ctx.getBean("blogUserService", BlogUserService.class);
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
    public void testGetBlogUserById() {
    }

    @Test
    public void testGetAllBlogUsers() {

        BlogUser chuck = new BlogUser();
        chuck.setDisplayName("chuck");
        chuck.setLoginName("chaz");
        chuck.setPassword("cDaddy");
        chuck = BuServ.addBlogUser(chuck);

        BlogUser hank = new BlogUser();
        hank.setDisplayName("hank");
        hank.setLoginName("hardon");
        hank.setPassword("Hdaddy");
        hank = BuServ.addBlogUser(hank);

        List<BlogUser> userList = BuServ.getAllBlogUsers();
        assertEquals(userList.size(), 2);

    }

    @Test
    public void testAddGetBlogUser() {

        BlogUser chuck = new BlogUser();
        chuck.setDisplayName("chuck");
        chuck.setLoginName("chaz");
        chuck.setPassword("cDaddy");
        chuck = BuServ.addBlogUser(chuck);

        BlogUser fromDao = BuServ.getBlogUserById(chuck.getBlogUserId());

        assertEquals(chuck.getDisplayName(), fromDao.getDisplayName());
        assertEquals(chuck.getLoginName(), fromDao.getLoginName());
        assertEquals(chuck.getPassword(), fromDao.getPassword());
        assertEquals(chuck.getBlogUserId(), fromDao.getBlogUserId());
    }

    @Test
    public void testEditBlogUser() {

        BlogUser chuck = new BlogUser();
        chuck.setDisplayName("chuck");
        chuck.setLoginName("chaz");
        chuck.setPassword("cDaddy");
   
        chuck = BuServ.addBlogUser(chuck);

        chuck.setLoginName("fuckYeah");
        BuServ.editBlogUser(chuck);

        BlogUser fromDao = BuServ.getBlogUserById(chuck.getBlogUserId());
        
        assertEquals(chuck.getLoginName(), fromDao.getLoginName());
    }

    @Test
    public void testDeleteBlogUser() {
    }

}
