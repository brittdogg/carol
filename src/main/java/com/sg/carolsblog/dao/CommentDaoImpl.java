/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.BlogUser;
import com.sg.carolsblog.dto.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import static org.springframework.http.RequestEntity.post;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trevor
 */
public class CommentDaoImpl implements CommentDao {
    
   private JdbcTemplate jdbcTemplate;
    
    public CommentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    private static final String SQL_SELECT_ALL_COMMENTS
            = "select bc.*, cb.blogUserId cbUserId, cb.displayName cbDisplayName, "
            + "cb.loginName cbLoginName, cb.userPassword cbUserPassword, "
            + "mb.blogUserId mbUserId, mb.displayName mbDisplayName, "
            + "mb.loginName mbLoginName, mb.userPassword mbUserPassword "
            + "from blogComment bc "
            + "join blogUser cb on cb.blogUserId = bc.createdBy "
            + "join blogUser mb on mb.blogUserId = bc.modifiedBy";
    
    private static final String SQL_SELECT_COMMENT_BY_ID
            = SQL_SELECT_ALL_COMMENTS + " where commentId = ?";
    
    private static final String SQL_INSERT_COMMENT
            = "insert into blogComment (blogPostId, content, createdBy, modifiedBy,"
            + "createdDate, modifiedDate) values (?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_UPDATE_COMMENT
            = "update blogComment set blogPostId = ?, content = ?, createdBy = ?, "
            + "modifiedBy = ?, createdDate = ?, modifiedDate = ? "
            + "where commentId = ?";
            
    private static final String SQL_DELETE_COMMENT
            = "delete from blogComment where commentId = ?";
    
    private static final String SQL_SELECT_COMMENT_BY_BLOGPOSTID
            =  SQL_SELECT_ALL_COMMENTS + " where blogPostId = ?";
    
    @Override
    public Comment getCommentById(int commentId) {
        try{
            return jdbcTemplate.queryForObject(
            SQL_SELECT_COMMENT_BY_ID, new CommentMapper(), commentId);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
   }

    @Override
    public List<Comment> getAllComments() {
       return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS,
               new CommentMapper()); 
   }

    @Override
    public List<Comment> getCommentsByPost(int postId) {
        return jdbcTemplate.query(SQL_SELECT_COMMENT_BY_BLOGPOSTID,
                new CommentMapper(), postId);
    }

    @Override
    @Transactional (propagation = Propagation.REQUIRED, readOnly = false)
    public Comment addComment(Comment comment) {
        jdbcTemplate.update(SQL_INSERT_COMMENT,
                comment.getBlogPostId(),
                comment.getContent(),
                comment.getCreatedBy().getBlogUserId(),
                comment.getModifiedBy().getBlogUserId(),
                comment.getCreatedDate().toString(),
                comment.getModifiedDate().toString());
        
        int commentId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()",
                Integer.class);
        
        comment.setCommentId(commentId);
        return comment;
   }

    @Override
    public void editComment(Comment comment) {
        jdbcTemplate.update(SQL_UPDATE_COMMENT,   
                comment.getBlogPostId(),
                comment.getContent(),
                comment.getCreatedBy().getBlogUserId(),
                comment.getModifiedBy().getBlogUserId(),
                comment.getCreatedDate().toString(),
                comment.getModifiedDate().toString(),
                comment.getCommentId());
   }

    @Override
    public void deleteComment(int commentId) {
        jdbcTemplate.update(SQL_DELETE_COMMENT, commentId);
   }
    
    private static final class CommentMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet rs, int i) throws SQLException {
            Comment comment = new Comment();
            
            comment.setCommentId(rs.getInt("commentId"));
            comment.setBlogPostId(rs.getInt("blogPostId"));
            comment.setContent(rs.getString("content"));
            
            comment.setCreatedDate(rs.getTimestamp("createdDate").toLocalDateTime());
            comment.setModifiedDate(rs.getTimestamp("modifiedDate").toLocalDateTime());
            
            // need to join twice to User - once for createdBy, once for modifiedBy
            
            BlogUser createdBy = new BlogUser();
            createdBy.setBlogUserId(rs.getInt("cbUserId"));
            createdBy.setDisplayName(rs.getString("cbDisplayName"));
            createdBy.setLoginName(rs.getString("cbLoginName"));
            createdBy.setPassword(rs.getString("cbUserPassword"));
            comment.setCreatedBy(createdBy);
            
            BlogUser modifiedBy = new BlogUser();
            modifiedBy.setBlogUserId(rs.getInt("mbUserId"));
            modifiedBy.setDisplayName(rs.getString("mbDisplayName"));
            modifiedBy.setLoginName(rs.getString("mbLoginName"));
            modifiedBy.setPassword(rs.getString("mbUserPassword"));
            comment.setModifiedBy(modifiedBy);
            
            return comment;
        }
        
    }
    
}
