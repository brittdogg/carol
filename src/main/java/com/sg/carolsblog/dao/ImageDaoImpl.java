/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Frederick
 */
public class ImageDaoImpl implements ImageDao {

    private JdbcTemplate jdbcTemplate;

    public ImageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_ADD_IMAGE
            = "insert into image (image, contenttype, result, status) "
            + "values (?, ?, ?, ?)";
    private static final String SQL_DELETE_IMAGE
            = "delete from image where imageid = ?";
    private static final String SQL_UPDATE_IMAGE
            = "update image set "
            + "image = ?, "
            + "contenttype = ?, "
            + "result = ?, "
            + "status = ? "
            + "where imageid = ?";
    private static final String SQL_SELECT_ALL_IMAGE
            = "select * from image";
    private static final String SQL_SELECT_IMAGE_BY_ID
            = SQL_SELECT_ALL_IMAGE + " where imageid = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addImage(Image image) {
        jdbcTemplate.update(SQL_ADD_IMAGE,
                image.getImage(),
                image.getContenttype(),
                image.getResult(),
                image.getStatus());
        int imageid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        image.setImageid(imageid);
    }

    @Override
    public void deleteImage(int imageid) {
        jdbcTemplate.update(SQL_DELETE_IMAGE, imageid);
    }

    @Override
    public void editImage(Image image) {
        jdbcTemplate.update(SQL_UPDATE_IMAGE,
                image.getImage(),
                image.getContenttype(),
                image.getResult(),
                image.getStatus(),
                image.getImageid());
    }

    @Override
    public List<Image> getAllImages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_IMAGE, new ImageMapper());
    }

    @Override
    public Image getImageById(int imageid) {
        try {
        return jdbcTemplate.queryForObject(
                SQL_SELECT_IMAGE_BY_ID, new ImageMapper(), imageid);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class ImageMapper implements RowMapper<Image> {

        @Override
        public Image mapRow(ResultSet rs, int i) throws SQLException {
            Image image = new Image();
            
            image.setImageid(rs.getInt("imageid"));
            image.setImage(rs.getBlob("image"));
            image.setContenttype(rs.getString("contenttype"));
            image.setResult(rs.getString("result"));
            image.setStatus(rs.getString("status"));
            
            return image;
        }
    }
}
