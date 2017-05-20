/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.StaticPage;
import java.util.List;

/**
 *
 * @author trevor
 */
public interface StaticPageDao {
    public StaticPage getPageById(int id);
    public List<StaticPage> getAllPages();
    public StaticPage addPage(StaticPage page);
    public void editPage(StaticPage page);
    public void deletePage(int pageId);
    public void approvePage(int pageId);
}
