/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.CreateTestData;
import com.sg.carolsblog.dto.Tag;
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
public class TagServiceTest {

    TagService tagServ;

    public TagServiceTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        tagServ = ctx.getBean("tagService", TagService.class);
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
    public void testAddGetTag() {

        Tag ted = new Tag();
        ted.setTagName("cheese");
        ted = tagServ.addTag(ted);

        Tag fromDao = tagServ.getTagById(ted.getTagId());
        assertEquals(ted.getTagName(), fromDao.getTagName());
        assertEquals(ted.getTagId(), fromDao.getTagId());
    }

    @Test
    public void testGetAllTags() {

        Tag ted = new Tag();
        ted.setTagName("cheese");
        ted = tagServ.addTag(ted);

        Tag tom = new Tag();
        tom.setTagName("sodium");
        tom = tagServ.addTag(tom);

        List<Tag> tagList = tagServ.getAllTags();
        assertEquals(tagList.size(), 2);
    }

    @Test
    public void testEditTag() {

        Tag ted = new Tag();
        ted.setTagName("cheese");
        ted = tagServ.addTag(ted);

        ted.setTagName("ham");
        tagServ.editTag(ted);

        Tag fromDao = tagServ.getTagById(ted.getTagId());
        assertEquals(ted.getTagName(), fromDao.getTagName());
    }

    @Test
    public void testDeleteTagById() {

        Tag ted = new Tag();
        ted.setTagName("cheese");
        ted = tagServ.addTag(ted);

        Tag tom = new Tag();
        tom.setTagName("sodium");
        tom = tagServ.addTag(tom);

        tagServ.deleteTag(tom.getTagId());
        List<Tag> tagList = tagServ.getAllTags();
        assertEquals(tagList.size(), 1);
    }

}
