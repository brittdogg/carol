/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.Tag;
import java.util.ArrayList;
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
public class TagDaoTest {

    TagDao tagDao;

    public TagDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        tagDao = ctx.getBean("tagDao", TagDao.class);
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

//        List<Tag> tags = tagDao.getAllTags();
//        for (Tag currentTag : tags) {
//            tagDao.deleteTag(currentTag.getTagId());
//        }

    }

    @After
    public void tearDown() {

//        List<Tag> tags = tagDao.getAllTags();
//        for (Tag currentTag : tags) {
//            tagDao.deleteTag(currentTag.getTagId());
//        }
    }

    @Test
    public void testDeleteTagById() {

        Tag ted = new Tag();
        ted.setTagName("cheese");
        ted = tagDao.addTag(ted);

        Tag tom = new Tag();
        tom.setTagName("sodium");
        tom = tagDao.addTag(tom);

        tagDao.deleteTag(tom.getTagId());
        List<Tag> tagList = tagDao.getAllTags();
        assertEquals(tagList.size(), 1);
    }

    @Test
    public void testGetAllTags() {
        Tag ted = new Tag();
        ted.setTagName("cheese");
        ted = tagDao.addTag(ted);

        Tag tom = new Tag();
        tom.setTagName("sodium");
        tom = tagDao.addTag(tom);

        List<Tag> tagList = tagDao.getAllTags();
        assertEquals(tagList.size(), 2);

    }

    @Test
    public void testAddGetTag() {

        Tag ted = new Tag();
        ted.setTagName("cheese");
        ted = tagDao.addTag(ted);

        Tag fromDao = tagDao.getTagById(ted.getTagId());
        assertEquals(ted.getTagName(), fromDao.getTagName());
        assertEquals(ted.getTagId(), fromDao.getTagId());
    }

    @Test
    public void testEditTag() {

        Tag ted = new Tag();
        ted.setTagName("cheese");
        ted = tagDao.addTag(ted);

        ted.setTagName("ham");
        tagDao.editTag(ted);

        Tag fromDao = tagDao.getTagById(ted.getTagId());
        assertEquals(ted.getTagName(), fromDao.getTagName());

    }
    
    @Test
    public void testAddAllTags() {
        Tag tag1 = new Tag();
        tag1.setTagName("newTag");
        
        Tag tag2 = new Tag();
        tag2.setTagName("AnotherNewTag");
        
        List<String> tags = new ArrayList<>();
        tags.add(tag2.getTagName());
        tags.add(tag1.getTagName());
        
        tagDao.addAllTags(tags);
        
    }

}
