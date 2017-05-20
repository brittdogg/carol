/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.BlogPost;
import com.sg.carolsblog.dto.BlogUser;
import com.sg.carolsblog.dto.Comment;
import java.time.LocalDateTime;
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
public class CommentDaoTest {
    
    CommentDao comDao;
    BlogPostDao bpDao;
    BlogUserDao buDao;
    BlogPost post1, post2;
    BlogUser user1;
    BlogUser user2;
    LocalDateTime date1, date2, date3, date4;
    
    CreateTestData createTestData = new CreateTestData();
    
    public CommentDaoTest() {
        
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        comDao = ctx.getBean("commentDao", CommentDao.class);
        bpDao = ctx.getBean("postDao", BlogPostDao.class);
        buDao = ctx.getBean("blogUserDao", BlogUserDao.class);
        
    }
    
   
    
    @Before
    public void setUp() {
        CreateTestData.rebuildDatabase();
        CreateTestData.loadTestData();
        
        date1 = LocalDateTime.of(2017, 03, 31, 12, 0);
        date2 = LocalDateTime.of(2017, 04, 1, 11, 30);
        date3 = LocalDateTime.of(2017, 04, 5, 9, 05);
        date4 = LocalDateTime.of(2017, 05, 31, 0, 0);
        
        user1 = buDao.getBlogUserById(1);
        user2 = buDao.getBlogUserById(2);
        
        post1 = bpDao.getPostById(1);
        post2 = bpDao.getPostById(2);
    }
    
    @After
    public void tearDown() {
    }


 

    @Test
    public void testGetAllComments() {
        
        Comment com1 = new Comment();
        com1.setBlogPostId(post1.getBlogPostId());
        com1.setContent("sweet cream and jelly beans");
        com1.setCreatedBy(user1);
        com1.setModifiedBy(user1);
        com1.setCreatedDate(date1);
        com1.setModifiedDate(date2);
        
        com1 = comDao.addComment(com1);
        
        Comment com2 = new Comment();
        com2.setBlogPostId(post2.getBlogPostId());
        com2.setContent("big snowman");
        com2.setCreatedBy(user2);
        com2.setModifiedBy(user1);
        com2.setCreatedDate(date1);
        com2.setModifiedDate(date2);
        
        com2 = comDao.addComment(com2);
        
        List<Comment> comList = comDao.getAllComments();
        assertEquals(comList.size(), 2);
    }


    @Test
    public void testGetCommentsByPost() {
        
        Comment com1 = new Comment();
        com1.setBlogPostId(post1.getBlogPostId());
        com1.setContent("sweet cream and jelly beans");
        com1.setCreatedBy(user1);
        com1.setModifiedBy(user1);
        com1.setCreatedDate(date1);
        com1.setModifiedDate(date2);
        
        com1 = comDao.addComment(com1);
        
        Comment com2 = new Comment();
        com2.setBlogPostId(post1.getBlogPostId());
        com2.setContent("big snowman");
        com2.setCreatedBy(user2);
        com2.setModifiedBy(user1);
        com2.setCreatedDate(date1);
        com2.setModifiedDate(date2);
        
        com2 = comDao.addComment(com2);
        
        List<Comment> comList = comDao.getCommentsByPost(post1.getBlogPostId());
        assertEquals(comList.size(), 2);
        
    }


    @Test
    public void testAddGetComment() {
        
        
        Comment com1 = new Comment();
        com1.setBlogPostId(post1.getBlogPostId());
        com1.setContent("sweet cream and jelly beans");
        com1.setCreatedBy(user1);
        com1.setModifiedBy(user1);
        com1.setCreatedDate(date1);
        com1.setModifiedDate(date2);
        
        com1 = comDao.addComment(com1);
        
        Comment fromDao = comDao.getCommentById(com1.getBlogPostId());
        
        assertEquals(com1.getBlogPostId(), fromDao.getBlogPostId());
        assertEquals(com1.getContent(), fromDao.getContent());
        assertEquals(com1.getCreatedBy().getBlogUserId(), fromDao.getCreatedBy().getBlogUserId());
        assertEquals(com1.getModifiedBy().getBlogUserId(), fromDao.getModifiedBy().getBlogUserId());
        assertEquals(com1.getCreatedDate(), fromDao.getCreatedDate());
        
    }


    @Test
    public void testEditComment() {
        
        Comment com1 = new Comment();
        com1.setBlogPostId(post1.getBlogPostId());
        com1.setContent("sweet cream and jelly beans");
        com1.setCreatedBy(user1);
        com1.setModifiedBy(user1);
        com1.setCreatedDate(date1);
        com1.setModifiedDate(date2);
        
        com1 = comDao.addComment(com1);
        
        com1.setContent("cow manure and a corvette");
        com1.setModifiedBy(user2);
        comDao.editComment(com1);
        
        Comment fromDao = comDao.getCommentById(com1.getCommentId());
        assertTrue(com1.getContent().equals(fromDao.getContent()));
        assertEquals(com1.getModifiedBy().getBlogUserId(), fromDao.getModifiedBy().getBlogUserId());
        
    }


    @Test
    public void testDeleteComment() {
        Comment com1 = new Comment();
        com1.setBlogPostId(post1.getBlogPostId());
        com1.setContent("sweet cream and jelly beans");
        com1.setCreatedBy(user1);
        com1.setModifiedBy(user1);
        com1.setCreatedDate(date1);
        com1.setModifiedDate(date2);
        
        com1 = comDao.addComment(com1);
        
        Comment com2 = new Comment();
        com2.setBlogPostId(post2.getBlogPostId());
        com2.setContent("big snowman");
        com2.setCreatedBy(user2);
        com2.setModifiedBy(user1);
        com2.setCreatedDate(date1);
        com2.setModifiedDate(date2);
        
        com2 = comDao.addComment(com2);
        
        comDao.deleteComment(com1.getCommentId());
        
        List<Comment> comList = comDao.getAllComments();
        assertEquals(comList.size(), 1);
               
    }

    
    
}
