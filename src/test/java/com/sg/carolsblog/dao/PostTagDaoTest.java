/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.BlogPost;
import com.sg.carolsblog.dto.PostTag;
import com.sg.carolsblog.dto.Tag;
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
 * @author bdogg
 */
public class PostTagDaoTest {
    
    PostTagDao ptDao;
    BlogPostDao postDao;
    BlogPost post1;
    TagDao tagDao;
    Tag tag1;
    
    public PostTagDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        ptDao = ctx.getBean("postTagDao", PostTagDao.class);
        tagDao = ctx.getBean("tagDao", TagDao.class);
        postDao = ctx.getBean("postDao", BlogPostDao.class);
        tag1 = tagDao.getTagById(1);
        post1 = postDao.getPostById(1);
        
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
        CreateTestData.loadTestData();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddAndGetPostTag() {
        PostTag postTag = new PostTag();
        postTag.setBlogPost(post1);
        postTag.setTag(tag1);
        postTag = ptDao.addPostTag(postTag);
        
        PostTag fromDao = ptDao.getPostTagById(postTag.getPostTagId());
        
        assertEquals(postTag.getPostTagId(),
                fromDao.getPostTagId());
        assertEquals(postTag.getTag().getTagId(),
                fromDao.getTag().getTagId());
        assertEquals(postTag.getBlogPost().getBlogPostId(),
                fromDao.getBlogPost().getBlogPostId());
        
    }
    
    @Test
    public void testAddPostTag_NoPost() {
        PostTag postTag = new PostTag();
        postTag.setTag(tag1);
        try {
            ptDao.addPostTag(postTag);
            fail("Improperly added relationship with no post.");
        } catch (NullPointerException e) {
            return;
        }
    }
    
    @Test
    public void testAddPostTag_NoTag() {
        PostTag postTag = new PostTag();
        postTag.setBlogPost(post1);
        try {
            ptDao.addPostTag(postTag);
            fail("Improperly added relationship with no tag.");
        } catch (NullPointerException e) {
            return;
        }
    }

//    @Test
//    public void testEditPostTag() {
//    }

    @Test
    public void testDeletePostTag() {
        assertNotNull(ptDao.getPostTagById(1));
        ptDao.deletePostTag(1);
        assertNull(ptDao.getPostTagById(1));
    }
    
    @Test
    public void testDeletePostTag_RelationshipDoesNotExist() {
        try {
            ptDao.deletePostTag(7);
        } catch (Exception e) {
            fail("Exception improperly thrown on delete of nonexistent relationship.");
        }
    }

    @Test
    public void testGetPostsForTag() {
        assertEquals(2, ptDao.getPostsForTag(1).size());
    }
    
    @Test
    public void testGetPostsForTag_TagDoesNotExist() {
        assertTrue(ptDao.getPostsForTag(7).isEmpty());
    }

    @Test
    public void testGetTagsForPost() {
        assertEquals(2, ptDao.getTagsForPost(2).size());
    }
    
    @Test
    public void testGetTagsForPost_PostDoesNotExist() {
        assertTrue(ptDao.getTagsForPost(7).isEmpty());
    }
    
    @Test
    public void testGetPostTagByRelationship() {
        PostTag expectedTag = ptDao.getPostTagById(1);
        PostTag postTag = ptDao.getPostTagByRelationship(2, 1);
        assertEquals(expectedTag.getBlogPost().getBlogPostId(), postTag.getBlogPost().getBlogPostId());
        assertEquals(expectedTag.getTag().getTagId(), postTag.getTag().getTagId());
    }
    
    // should have tests/test data that checks case sensitivity
    
}
