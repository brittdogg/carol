/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.BlogPost;
import com.sg.carolsblog.dto.Category;
import com.sg.carolsblog.dto.BlogUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trevor
 */
public class BlogPostDaoImpl implements BlogPostDao {

    private static final String SQL_SELECT_ALL_BLOG_POSTS
            = "SELECT bp.*, c.*, uc.blogUserId cbUserId, uc.displayName cbDisplayName, uc.loginName cbLoginName, uc.userPassword cbPassword, "
            + "um.blogUserId mbUserId, um.displayName mbDisplayName, um.loginName mbLoginName, um.userPassword mbPassword FROM blogPost bp "
            + "INNER JOIN category c ON bp.categoryId = c.categoryId "
            + "INNER JOIN blogUser uc ON uc.blogUserId = bp.createdBy "
            + "INNER JOIN blogUser um ON um.blogUserId = bp.modifiedBy";
    
    private static final String SQL_ORDER_BY_DATE = " ORDER BY bp.publishDate DESC";

    private static final String SQL_FILTER_BY_CATEGORY = " WHERE c.categoryId = ?";

    private static final String SQL_FILTER_BY_AUTHOR = " WHERE uc.blogUserId = ?";

    private static final String SQL_SELECT_BLOG_POST_BY_ID
            = SQL_SELECT_ALL_BLOG_POSTS + " WHERE bp.blogPostId = ?";

    private static final String SQL_SELECT_BLOG_POST_BY_CATEGORY_ID
            = SQL_SELECT_ALL_BLOG_POSTS + SQL_FILTER_BY_CATEGORY + SQL_ORDER_BY_DATE;

    private static final String SQL_SELECT_BLOG_POST_BY_AUTHOR_ID
            = SQL_SELECT_ALL_BLOG_POSTS + SQL_FILTER_BY_AUTHOR + SQL_ORDER_BY_DATE;

    private static final String SQL_INSERT_BLOG_POST
            = "INSERT INTO blogPost (createdDate, modifiedDate, publishDate, "
            + "expirationDate, title, content, urlAlias, categoryId, "
            + "createdBy, modifiedBy, approved) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_BLOG_POST
            = "UPDATE blogPost SET createdDate = ?, content = ?, title = ?, "
            + "publishDate = ?, expirationDate = ?, modifiedDate = ?, "
            + "approved = ?, categoryId = ?, createdBy = ?, modifiedBy = ?, "
            + "urlAlias = ? "
            + "WHERE blogPostId = ?";

    private static final String SQL_DELETE_BLOG_POST
            = "DELETE FROM blogPost WHERE blogPostId = ?";
    
    private static final String SQL_GET_POST_BY_TAG_NAME
            = "SELECT bp.*, c.*, uc.blogUserId cbUserId, uc.displayName cbDisplayName, uc.loginName cbLoginName, uc.userPassword cbPassword, "
            + "um.blogUserId mbUserId, um.displayName mbDisplayName, um.loginName mbLoginName, um.userPassword mbPassword FROM blogPost bp "
            + "INNER JOIN category c ON bp.categoryId = c.categoryId "
            + "INNER JOIN blogUser uc ON uc.blogUserId = bp.createdBy "
            + "INNER JOIN blogUser um ON um.blogUserId = bp.modifiedBy "
            + "INNER JOIN postTag pt ON bp.blogPostId = pt.blogPostId "
            + "INNER JOIN tag ON pt.tagId = tag.tagId "
            + "WHERE tag.tagName = ?";

    private final JdbcTemplate jdbcTemplate;

    public BlogPostDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BlogPost getPostById(int postId) {
        try {
            return jdbcTemplate.queryForObject(
                    SQL_SELECT_BLOG_POST_BY_ID, new BlogPostMapper(), postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<BlogPost> getAllPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BLOG_POSTS + SQL_ORDER_BY_DATE, new BlogPostMapper());
    }

    @Override
    public List<BlogPost> getPostsByTag(String tagName) {
        return jdbcTemplate.query(SQL_GET_POST_BY_TAG_NAME + SQL_ORDER_BY_DATE, 
                new BlogPostMapper(),
                tagName);
    }

    @Override
    public List<BlogPost> getPostsByCategory(int categoryId) {
        return jdbcTemplate.query(SQL_SELECT_BLOG_POST_BY_CATEGORY_ID,
                new BlogPostMapper(), categoryId);
    }

    @Override
    public List<BlogPost> getPostsByUser(int userId) {
        return jdbcTemplate.query(SQL_SELECT_BLOG_POST_BY_AUTHOR_ID,
                new BlogPostMapper(), userId);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public BlogPost addPost(BlogPost post) {

        // pass null for optional field if not filled out
        String expireDate = null;
        if (post.getExpirationDate() != null) {
            expireDate = post.getExpirationDate().toString();
        }

        jdbcTemplate.update(SQL_INSERT_BLOG_POST,
                post.getCreatedDate().toString(),
                post.getModifiedDate().toString(),
                post.getPublishDate().toString(),
                expireDate,
                post.getTitle(),
                post.getContent(),
                post.getUrlAlias(),
                post.getCategory().getCategoryId(),
                post.getCreatedBy().getBlogUserId(),
                post.getModifiedBy().getBlogUserId(),
                post.getApproved());

        int postId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        post.setBlogPostId(postId);
        return post;
    }

    @Override
    public void editPost(BlogPost post) {

        // handle optional fields
        String expireDate = null;
        if (post.getExpirationDate() != null) {
            expireDate = post.getExpirationDate().toString();
        }

        jdbcTemplate.update(SQL_UPDATE_BLOG_POST,
                post.getCreatedDate().toString(),
                post.getContent(),
                post.getTitle(),
                post.getPublishDate().toString(),
                expireDate,
                post.getModifiedDate().toString(),
                post.getApproved(),
                post.getCategory().getCategoryId(),
                post.getCreatedBy().getBlogUserId(),
                post.getModifiedBy().getBlogUserId(),
                post.getUrlAlias(),
                post.getBlogPostId()
        );
    }

    @Override
    public void deletePost(int postId) {
        jdbcTemplate.update(SQL_DELETE_BLOG_POST, postId);
        // TODO throw exception if an issue, e.g. foreign key constraint issue?
        // that can happen if post-tag relationship are not removed first
        // (service should handle that).
        // translate to custom exception?
    }

    @Override
    public void approvePost(int postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class BlogPostMapper implements RowMapper<BlogPost> {

        @Override
        public BlogPost mapRow(ResultSet rs, int i) throws SQLException {
            BlogPost post = new BlogPost();

            post.setBlogPostId(rs.getInt("blogPostId"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setUrlAlias(rs.getString("urlAlias"));
            post.setApproved(rs.getBoolean("approved"));

            // handle required dates
            post.setCreatedDate(rs.getTimestamp("createdDate").toLocalDateTime());
            post.setModifiedDate(rs.getTimestamp("modifiedDate").toLocalDateTime());

            // handle optional dates
            Timestamp pubDate = rs.getTimestamp("publishDate");
            if (pubDate != null) {
                post.setPublishDate(pubDate.toLocalDateTime());
            }
            Timestamp expDate = rs.getTimestamp("expirationDate");
            if (expDate != null) {
                post.setExpirationDate(expDate.toLocalDateTime());
            }

            // build category
            Category category = new Category();
            category.setCategoryId(rs.getInt("categoryId"));
            category.setCategoryName(rs.getString("categoryName"));
            post.setCategory(category);

            // need to join twice to User - once for createdBy, once for modifiedBy
            BlogUser createdBy = new BlogUser();
            createdBy.setBlogUserId(rs.getInt("cbUserId"));
            createdBy.setDisplayName(rs.getString("cbDisplayName"));
            createdBy.setLoginName(rs.getString("cbLoginName"));
            createdBy.setPassword(rs.getString("cbPassword"));
            post.setCreatedBy(createdBy);

            BlogUser modifiedBy = new BlogUser();
            modifiedBy.setBlogUserId(rs.getInt("mbUserId"));
            modifiedBy.setDisplayName(rs.getString("mbDisplayName"));
            modifiedBy.setLoginName(rs.getString("mbLoginName"));
            modifiedBy.setPassword(rs.getString("mbPassword"));
            post.setModifiedBy(modifiedBy);

            return post;
        }

    }

}
