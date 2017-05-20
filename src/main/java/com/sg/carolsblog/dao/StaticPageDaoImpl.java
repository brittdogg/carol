/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.StaticPage;
import com.sg.carolsblog.dto.BlogUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trevor
 */
public class StaticPageDaoImpl implements StaticPageDao {

    private JdbcTemplate jdbcTemplate;
    
    @Inject
    public StaticPageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_GET_PAGE_BY_ID
            = "select sp.*, cb.blogUserId cbId, cb.displayName cbName, "
            + "cb.loginName cbLogin, cb.userPassword cbPassword, "
            + "mb.blogUserId mbId, mb.displayName mbName, cb.loginName mbLogin, "
            + "mb.userPassword mbPassword from staticPage sp "
            + "inner join blogUser cb on sp.createdBy = cb.blogUserId "
            + "inner join blogUser mb on sp.modifiedBy = mb.blogUserId "
            + "where sp.staticPageId = ?";
    
    private static final String SQL_GET_ALL_PAGES
            = "select sp.*, cb.blogUserId cbId, cb.displayName cbName, "
            + "cb.loginName cbLogin, cb.userPassword cbPassword, "
            + "mb.blogUserId mbId, mb.displayName mbName, cb.loginName mbLogin, "
            + "mb.userPassword mbPassword from staticPage sp "
            + "inner join blogUser cb on sp.createdBy = cb.blogUserId "
            + "inner join blogUser mb on sp.modifiedBy = mb.blogUserId";
    
    private static final String SQL_INSERT_PAGE
            = "insert into staticPage (urlAlias, title, content, pageOrder, "
            + "createdDate, modifiedDate, createdBy, modifiedBy) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE_STATIC_PAGE
            = "update staticPage set urlAlias = ?, title = ?, content = ?, pageOrder = ?, "
            + "createdDate = ?, modifiedDate = ?, createdBy = ?, modifiedBy = ? "
            + "where staticPageId = ?";
    
    private static final String SQL_DELETE_STATIC_PAGE_BY_ID
            = "delete from staticPage where staticPageId = ?";
    
    @Override
    public StaticPage getPageById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_PAGE_BY_ID, 
                    new StaticPageRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<StaticPage> getAllPages() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL_PAGES, new StaticPageRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Transactional (propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public StaticPage addPage(StaticPage page) {
        jdbcTemplate.update(SQL_INSERT_PAGE, 
                page.getUrlAlias(),
                page.getTitle(),
                page.getContent(),
                page.getPageOrder(),
                page.getCreatedDate().toString(),
                page.getModifiedDate().toString(),
                page.getCreatedBy().getBlogUserId(),
                page.getModifiedBy().getBlogUserId());
        // Get the id of the newly inserted object
//        int pageId = jdbcTemplate.queryForObject(
//                "select LAST_INSERT_ID()", Integer.class);
        int pageId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        page.setStaticPageId(pageId);
        
        return page;
    }

    @Override
    public void editPage(StaticPage page) {
        jdbcTemplate.update(SQL_UPDATE_STATIC_PAGE, 
                page.getUrlAlias(),
                page.getTitle(), 
                page.getContent(), 
                page.getPageOrder(),
                page.getCreatedDate().toString(), 
                page.getModifiedDate().toString(), 
                page.getCreatedBy().getBlogUserId(),
                page.getModifiedBy().getBlogUserId(), 
                page.getStaticPageId());
    }

    @Override
    public void deletePage(int pageId) {
        jdbcTemplate.update(SQL_DELETE_STATIC_PAGE_BY_ID, pageId);
    }

    @Override
    public void approvePage(int pageId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static final class StaticPageRowMapper implements RowMapper<StaticPage> {

        @Override
        public StaticPage mapRow(ResultSet rs, int i) throws SQLException {
            StaticPage page = new StaticPage();
            BlogUser createdBy = new BlogUser();
            BlogUser modifiedBy = new BlogUser();
            
            page.setStaticPageId(rs.getInt("sp.staticPageId"));
            page.setUrlAlias(rs.getString("sp.urlAlias"));
            page.setTitle(rs.getString("sp.title"));
            page.setContent(rs.getString("sp.content"));
            page.setPageOrder(rs.getInt("sp.pageOrder"));
            page.setCreatedDate(rs.getTimestamp("sp.createdDate").toLocalDateTime());
            page.setModifiedDate(rs.getTimestamp("sp.modifiedDate").toLocalDateTime());
            // Build created By user (bu1)
            createdBy.setBlogUserId(rs.getInt("cbId"));
            createdBy.setDisplayName(rs.getString("cbName"));
            createdBy.setLoginName(rs.getString("cbLogin"));
            createdBy.setPassword(rs.getString("cbPassword"));
            // Set the created by user
            page.setCreatedBy(createdBy);
            // Build modified By user (bu2)
            modifiedBy.setBlogUserId(rs.getInt("mbId"));
            modifiedBy.setDisplayName(rs.getString("mbName"));
            modifiedBy.setLoginName(rs.getString("mbLogin"));
            modifiedBy.setPassword(rs.getString("mbPassword"));
            // Set the modified by user
            page.setModifiedBy(modifiedBy);
            return page;
        }
        
    }
    
}
