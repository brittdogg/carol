/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.BlogUserDao;
import com.sg.carolsblog.dao.CreateTestData;
import com.sg.carolsblog.dao.StaticPageDao;
import com.sg.carolsblog.dto.BlogUser;
import com.sg.carolsblog.dto.StaticPage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author trevor
 */
public class StaticPageServiceImplTest {
    
    private StaticPageService service;
    private BlogUserDao blogUserDao;
    private DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public StaticPageServiceImplTest() {
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        this.service = ctx.getBean("pageService", StaticPageService.class);
        this.blogUserDao = ctx.getBean("blogUserDao", BlogUserDao.class);
    }
    
    @Before
    public void setUp() {
        CreateTestData.rebuildDatabase();
        CreateTestData.loadTestData();
    }

    @Test
    public void testSomeMethod() {
    }
    
    @Test
    public void testAddGetPost() {
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
        page = service.addPage(page);
        
        // Get the page from the database
        StaticPage fromDao = service.getPageById(page.getStaticPageId());
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
        // Get a page from the dao
        StaticPage fromService = null;
        fromService = service.getPageById(1);
        assertNotNull(fromService);
        
        // Delete the page and show its gone
        service.deletePage(1);
        fromService = service.getPageById(1);
        assertNull(fromService);
    }
    
    @Test
    public void testEditPage() {
        // Get a pre populated page from the database
        StaticPage fromService = service.getPageById(1);
        
        // Create a new static page and set its properties to be equal to fromDao
        StaticPage page = new StaticPage();
        page.setContent(fromService.getContent());
        page.setCreatedBy(fromService.getCreatedBy());
        page.setCreatedDate(fromService.getCreatedDate());
        page.setModifiedBy(fromService.getModifiedBy());
        page.setModifiedDate(fromService.getModifiedDate());
        page.setPageOrder(fromService.getPageOrder());
        page.setStaticPageId(fromService.getStaticPageId());
        page.setTitle(fromService.getTitle());
        page.setUrlAlias(fromService.getUrlAlias());
        
        // Show they are the same
        assertEquals(fromService.getStaticPageId(), page.getStaticPageId());
        assertEquals(fromService.getContent(), page.getContent());
        assertEquals(fromService.getCreatedBy().getBlogUserId(), page.getCreatedBy().getBlogUserId());
        assertEquals(fromService.getCreatedDate(), page.getCreatedDate());
        assertEquals(fromService.getModifiedBy().getBlogUserId(), page.getModifiedBy().getBlogUserId());
        assertEquals(fromService.getModifiedDate(), page.getModifiedDate());
        assertEquals(fromService.getPageOrder(), page.getPageOrder());
        assertEquals(fromService.getTitle(), page.getTitle());
        assertEquals(fromService.getUrlAlias(), page.getUrlAlias());
        
        // Change a field and show they are different
        page.setContent("this content has been edited for testing purposes.");
        assertNotEquals(fromService.getContent(), page.getContent());
        
        // Edit the database and update the fromDao static page
        service.editPage(page);
        fromService = service.getPageById(page.getStaticPageId());
        
        // Show that they are now the same
        assertEquals(fromService.getStaticPageId(), page.getStaticPageId());
        assertEquals(fromService.getContent(), page.getContent());
        assertEquals(fromService.getCreatedBy().getBlogUserId(), page.getCreatedBy().getBlogUserId());
        assertEquals(fromService.getCreatedDate(), page.getCreatedDate());
        assertEquals(fromService.getModifiedBy().getBlogUserId(), page.getModifiedBy().getBlogUserId());
        assertNotEquals(fromService.getModifiedDate(), page.getModifiedDate());
        assertEquals(fromService.getPageOrder(), page.getPageOrder());
        assertEquals(fromService.getTitle(), page.getTitle());
        assertEquals(fromService.getUrlAlias(), page.getUrlAlias());
    }
}
