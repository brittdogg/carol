/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.BlogPost;
import com.sg.carolsblog.dto.Category;
import com.sg.carolsblog.dto.PostTag;
import com.sg.carolsblog.dto.Tag;
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
public class PostTagDaoImpl implements PostTagDao {

    private static final String SQL_INSERT_POSTTAG_FOR_POST
            = "INSERT INTO postTag (`tagId`, `blogPostId`) "
            + "VALUES (?, ?)";

//    private static final String SQL_INSERT_POSTTAG_FOR_PAGE
//            = "INSERT INTO PostTag (`tagId`, `staticPageId`) "
//            + "VALUES (?, ?)";
    private static final String SQL_DELETE_POSTTAG
            = "DELETE FROM postTag WHERE postTagId = ?";

//    private static final String SQL_GET_ALL_POSTTAGS_FOR_POSTS_AND_PAGES
//            = "SELECT pt.*, t.tagName, bp.createdDate, bp.modifiedDate, "
//            + "    bp.publishDate, bp.expirationDate, bp.title, bp.content "
//            + "	   bp.urlAlias, bp.approved, bp.categoryId, c.categoryName, "
//            + "    bp.createdBy, bp.modifiedBy, "
//            + "    sp.createdDate, sp.modifiedDate, sp.createdBy, sp.modifiedBy, "
//            + "    sp.title, sp.content, sp.urlAlias, sp.pageOrder "
//            + "FROM postTag pt "
//            + "LEFT JOIN tag t on pt.tagId = t.tagId "
//            + "LEFT JOIN blogPost bp on pt.blogPostId = bp.blogPostId "
//            + "LEFT JOIN staticPage sp on pt.staticPageId = sp.staticPageId "
//            + "LEFT JOIN category c on bp.categoryId = c.categoryId";
    private static final String SQL_GET_ALL_POSTTAGS_FOR_POSTS
            = "SELECT pt.*, bp.*, c.categoryName, t.tagName "
            + "FROM postTag pt "
            + "INNER JOIN blogPost bp on pt.blogPostId = bp.blogPostId "
            + "INNER JOIN tag t on pt.tagId = t.tagId "
            + "INNER JOIN category c on bp.categoryId = c.categoryId ";

    private static final String SQL_GET_POSTTAG_BY_ID
            = SQL_GET_ALL_POSTTAGS_FOR_POSTS
            + " WHERE pt.postTagId = ?";
    
    private static final String SQL_GET_POSTTAG_BY_RELATIONSHIP
            = SQL_GET_ALL_POSTTAGS_FOR_POSTS
            + " WHERE pt.blogPostId = ? AND pt.tagId = ?";

//    private static final String SQL_GET_ALL_POSTTAGS_FOR_PAGES
//            = "SELECT pt.*, sp.* FROM postTag pt "
//            + "INNER JOIN staticPage sp on pt.staticPageId = sp.staticPageId";
    private static final String SQL_GET_POSTS_BY_TAG
            = SQL_GET_ALL_POSTTAGS_FOR_POSTS
            + " WHERE pt.tagId = ?";

//    private static final String SQL_GET_PAGES_BY_TAG
//            = SQL_GET_ALL_POSTTAGS_FOR_PAGES
//            + " WHERE pt.tagId = ?";
    private static final String SQL_GET_TAGS_BY_POST
            = SQL_GET_ALL_POSTTAGS_FOR_POSTS
            + "WHERE pt.blogPostId = ?";

//    private static final String SQL_GET_TAGS_BY_PAGE
//            = SQL_GET_ALL_POSTTAGS_FOR_PAGES
//            + "WHERE pt.staticPageId = ?";
    JdbcTemplate jdbcTemplate;

    public PostTagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PostTag getPostTagById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_POSTTAG_BY_ID, new PostTagMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public PostTag getPostTagByRelationship(int postId, int tagId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_POSTTAG_BY_RELATIONSHIP, 
                    new PostTagMapper(), postId, tagId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public PostTag addPostTag(PostTag postTag) {

        jdbcTemplate.update(SQL_INSERT_POSTTAG_FOR_POST,
                postTag.getTag().getTagId(),
                postTag.getBlogPost().getBlogPostId());

        int id = jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Integer.class);
        postTag.setPostTagId(id);

        return postTag;
    }

    @Override
    public void editPostTag(PostTag postTag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePostTag(int id) {
        jdbcTemplate.update(SQL_DELETE_POSTTAG, id);
        // will throw exception if foreign key constraint fails
    }

    @Override
    public List<PostTag> getPostsForTag(int tagId) {
        return jdbcTemplate.query(SQL_GET_POSTS_BY_TAG, new PostTagMapper(), tagId);
    }

    @Override
    public List<PostTag> getTagsForPost(int postId) {
        return jdbcTemplate.query(SQL_GET_TAGS_BY_POST, new PostTagMapper(), postId);
    }

    private static final class PostTagMapper implements RowMapper<PostTag> {

        @Override
        public PostTag mapRow(ResultSet rs, int i) throws SQLException {
            PostTag pt = new PostTag();

            pt.setPostTagId(rs.getInt("pt.postTagId"));

            // build tag
            Tag tag = new Tag();
            tag.setTagId(rs.getInt("pt.tagId"));
            tag.setTagName(rs.getString("t.tagName"));
            pt.setTag(tag);

            //build blog post
            Integer postId = rs.getInt("pt.blogPostId");
            BlogPost post = new BlogPost();
            post.setBlogPostId(postId);

            post.setTitle(rs.getString("bp.title"));
            post.setContent(rs.getString("bp.content"));
            post.setUrlAlias(rs.getString("bp.urlAlias"));
            post.setApproved(rs.getBoolean("bp.approved"));

            // handle required dates
            post.setCreatedDate(rs.getTimestamp("bp.createdDate").toLocalDateTime());
            post.setModifiedDate(rs.getTimestamp("bp.modifiedDate").toLocalDateTime());

            // handle optional dates
            Timestamp pubDate = rs.getTimestamp("bp.publishDate");
            if (pubDate != null) {
                post.setPublishDate(pubDate.toLocalDateTime());
            }
            Timestamp expDate = rs.getTimestamp("bp.expirationDate");
            if (expDate != null) {
                post.setExpirationDate(expDate.toLocalDateTime());
            }

            // build category
            Category category = new Category();
            category.setCategoryId(rs.getInt("bp.categoryId"));
            category.setCategoryName(rs.getString("c.categoryName"));
            post.setCategory(category);

            pt.setBlogPost(post);

            return pt;
        }

    }

}
