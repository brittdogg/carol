/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class TagDaoImpl implements TagDao {
    
     private JdbcTemplate jdbcTemplate;

    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_SELECT_TAG
            ="select * from tag where tagId = ?";
    
    private static final String SQL_SELECT_TAG_BY_NAME
            ="select * from tag where tagName = ?";
    
    private static final String SQL_SELECT_ALL_TAGS
            ="select * from tag";
    
    private static final String SQL_INSERT_TAG
            ="insert into tag (tagName) "
            + "values (?)";
    
    private static final String SQL_UPDATE_TAG
            ="update tag set tagName = ? where tagId = ?";
    
    private static final String SQL_DELETE_TAG
            ="delete from tag where tagId = ?";
    
    private static final String SQL_TAG_COUNT_BY_NAME
            ="select count(*) from tag where tagName = ?";

    @Override
    public Tag getTagById(int tagId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG,
                    new TagMapper(), tagId);
        }catch (EmptyResultDataAccessException ex) {
            return null;
        }
   }
    
     @Override
    public Tag getTagByName(String tagName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG_BY_NAME,
                    new TagMapper(), tagName);
        }catch (EmptyResultDataAccessException ex) {
            return null;
        }
   }
    
    

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS,
                new TagMapper());
   }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag addTag(Tag tag) {
        jdbcTemplate.update(SQL_INSERT_TAG, tag.getTagName());
        
        int tagId
                = jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", Integer.class);
        tag.setTagId(tagId);
        return tag;
        
   }
    
    @Override
    public void addAllTags(List<String> tagNames) {
        // TODO write this method body
        for(String tagName : tagNames) {
            Tag tag = new Tag();
            tag.setTagName(tagName);
            // get count of rows with given tagName
            Integer tagCount;
            tagCount = getTagCount(tagName);
            // if the tag is not in the database add it
            if(tagCount == null || tagCount == 0) {
                addTag(tag);
            }
        }
    }

    @Override
    public void editTag(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_TAG,
                tag.getTagName(),
                tag.getTagId());
    }

    @Override
    public void deleteTag(int tagId) {
        jdbcTemplate.update(SQL_DELETE_TAG, tagId);
   }
    
    private Integer getTagCount(String tagName) {
        return jdbcTemplate.queryForObject(SQL_TAG_COUNT_BY_NAME, Integer.class, tagName);
    }
    
    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag tag = new Tag();
            tag.setTagName(rs.getString("tagName"));
            tag.setTagId(rs.getInt("tagId"));
            
            return tag;
       }
        
    }
}
