/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.StaticPageDao;
import com.sg.carolsblog.dto.StaticPage;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author trevor
 */
public class StaticPageServiceImpl implements StaticPageService {
    
    private StaticPageDao dao;

    public StaticPageServiceImpl(StaticPageDao dao) {
        this.dao = dao;
    }

    @Override
    public StaticPage getPageById(int id) {
        return dao.getPageById(id);
    }

    @Override
    public List<StaticPage> getAllPages() {
        return dao.getAllPages();
    }

    @Override
    public StaticPage addPage(StaticPage page) {
        return dao.addPage(page);
    }

    @Override
    public void editPage(StaticPage page) {
        // Get the current page from the database
        StaticPage fromDao = dao.getPageById(page.getStaticPageId());
        
        // Set the properties that can not be changed by the user
        page.setCreatedBy(fromDao.getCreatedBy());
        page.setCreatedDate(fromDao.getCreatedDate());
        
        // Get the current time and update the last modified
        LocalDateTime now = LocalDateTime.now();
        page.setModifiedDate(now);
        
        // TODO Set the user that modified the page
        page.setModifiedBy(fromDao.getModifiedBy());
        
        dao.editPage(page);
    }

    @Override
    public void deletePage(int pageId) {
        dao.deletePage(pageId);
    }

    @Override
    public void approvePage(int pageId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
