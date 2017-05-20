/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.BlogUser;
import com.sg.carolsblog.dto.StaticPage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author trevor
 */
public class StaticPageDaoImplTest {
    
    private StaticPageDao dao;
    private BlogUserDao blogUserDao;
    private DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Inject
    public void setDao(StaticPageDao dao, BlogUserDao blogUserDao) {
        this.dao = dao;
        this.blogUserDao = blogUserDao;
    }
    
    public StaticPageDaoImplTest() {
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        this.dao = ctx.getBean("pageDao", StaticPageDao.class);
        this.blogUserDao = ctx.getBean("blogUserDao", BlogUserDao.class);
    }
    
    @Before
    public void setUp() {
        
        CreateTestData.rebuildDatabase();
        CreateTestData.loadTestData();
    }

    @Test
    public void testAddGetPageById() {
        LocalDateTime now = LocalDateTime.parse("2017-04-08 13:45:09", formatter);
        
        BlogUser blogUser = blogUserDao.getBlogUserById(1);
        

//        BlogUser blogUser = new BlogUser();
//        blogUser.setDisplayName("User Display Name");
//        blogUser.setLoginName("myLogin49837");
//        blogUser.setPassword("itsapassword");
//        // Add the blog user
//        blogUserDao.addBlogUser(blogUser);
        
        StaticPage page = new StaticPage();
        page.setUrlAlias("url-slug");
        page.setTitle("This is a page title");
        page.setContent("This is the page content. It's the stuff written on the page");
        page.setPageOrder(8);
        page.setCreatedDate(now);
        page.setModifiedDate(now);
        page.setCreatedBy(blogUser);
        page.setModifiedBy(blogUser);
        // add the static page
        page = dao.addPage(page);
        
        // Get the page from the database
        StaticPage fromDao = dao.getPageById(page.getStaticPageId());
        assertEquals(fromDao.getStaticPageId(), page.getStaticPageId());
        assertEquals(fromDao.getContent(), page.getContent());
        assertEquals(fromDao.getCreatedBy().getBlogUserId(), page.getCreatedBy().getBlogUserId());
        assertEquals(fromDao.getCreatedDate(), page.getCreatedDate());
        assertEquals(fromDao.getModifiedBy().getBlogUserId(), page.getModifiedBy().getBlogUserId());
        assertEquals(fromDao.getModifiedDate(), page.getModifiedDate());
        assertEquals(fromDao.getPageOrder(), page.getPageOrder());
        assertEquals(fromDao.getTitle(), page.getTitle());
        assertEquals(fromDao.getUrlAlias(), page.getUrlAlias());
    }
    
    @Test
    public void testGetAllPages() {
        // Todo add a counter for num of static pages?
        List<StaticPage> pages = dao.getAllPages();
        assertTrue(pages.size() == 2);
    }

    @Test
    public void testEditPage() {
        // Get a pre populated page from the database
        StaticPage fromDao = dao.getPageById(1);
        
        // Create a new static page and set its properties to be equal to fromDao
        StaticPage page = new StaticPage();
        page.setContent(fromDao.getContent());
        page.setCreatedBy(fromDao.getCreatedBy());
        page.setCreatedDate(fromDao.getCreatedDate());
        page.setModifiedBy(fromDao.getModifiedBy());
        page.setModifiedDate(fromDao.getModifiedDate());
        page.setPageOrder(fromDao.getPageOrder());
        page.setStaticPageId(fromDao.getStaticPageId());
        page.setTitle(fromDao.getTitle());
        page.setUrlAlias(fromDao.getUrlAlias());
        
        // Show they are the same
        assertEquals(fromDao.getStaticPageId(), page.getStaticPageId());
        assertEquals(fromDao.getContent(), page.getContent());
        assertEquals(fromDao.getCreatedBy().getBlogUserId(), page.getCreatedBy().getBlogUserId());
        assertEquals(fromDao.getCreatedDate(), page.getCreatedDate());
        assertEquals(fromDao.getModifiedBy().getBlogUserId(), page.getModifiedBy().getBlogUserId());
        assertEquals(fromDao.getModifiedDate(), page.getModifiedDate());
        assertEquals(fromDao.getPageOrder(), page.getPageOrder());
        assertEquals(fromDao.getTitle(), page.getTitle());
        assertEquals(fromDao.getUrlAlias(), page.getUrlAlias());
        
        // Change a field and show they are different
        page.setContent("this content has been edited for testing purposes.");
        assertNotEquals(fromDao.getContent(), page.getContent());
        
        // Edit the database and update the fromDao static page
        dao.editPage(page);
        fromDao = dao.getPageById(page.getStaticPageId());
        
        // Show that they are now the same
        assertEquals(fromDao.getStaticPageId(), page.getStaticPageId());
        assertEquals(fromDao.getContent(), page.getContent());
        assertEquals(fromDao.getCreatedBy().getBlogUserId(), page.getCreatedBy().getBlogUserId());
        assertEquals(fromDao.getCreatedDate(), page.getCreatedDate());
        assertEquals(fromDao.getModifiedBy().getBlogUserId(), page.getModifiedBy().getBlogUserId());
        assertEquals(fromDao.getModifiedDate(), page.getModifiedDate());
        assertEquals(fromDao.getPageOrder(), page.getPageOrder());
        assertEquals(fromDao.getTitle(), page.getTitle());
        assertEquals(fromDao.getUrlAlias(), page.getUrlAlias());
    }

    @Test
    public void testDeletePage() {
        // Get a page from the dao and show it's not null
        StaticPage fromDao = null;
        fromDao = dao.getPageById(1);
        assertNotNull(fromDao);
        
        // Delete the page and show its null when queried for
        dao.deletePage(fromDao.getStaticPageId());
        fromDao = dao.getPageById(fromDao.getStaticPageId());
        assertNull(fromDao);
    }

//    @Test
//    public void testApprovePage() {
//    }
    
}