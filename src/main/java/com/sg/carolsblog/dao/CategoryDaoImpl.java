/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;


import com.sg.carolsblog.dto.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sg.carolsblog.dto.Category;

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
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_SELECT_CATEGORY
            = "select * from category where categoryId = ?";
    
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from category";
    
    private static final String SQL_INSERT_CATEGORY
            ="insert into category (categoryName, categoryId) "
            + "values (?, ?)";
    
    private static final String SQL_UPDATE_CATEGORY
            ="update category set categoryName = ? where categoryId = ?";
    
    private static final String SQL_DELETE_CATEGORY
            ="delete from category where categoryId = ?";
    
    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY,
                    new CategoryMapper(), categoryId);
        }catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES,
                new CategoryMapper());        
   }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category addCategory(Category category) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY,
                category.getCategoryName(),
                category.getCategoryId());
        
        int categoryId
                =jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);
        category.setCategoryId(categoryId);
        return category;       
   }

    @Override
    public void editCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY,
               category.getCategoryName(),
               category.getCategoryId());
   }

    @Override
    public void deleteCategory(int categoryId) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
   }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category cat = new Category();
            cat.setCategoryName(rs.getString("categoryName"));
            cat.setCategoryId(rs.getInt("categoryId"));

            return cat;
        }

    }
}
