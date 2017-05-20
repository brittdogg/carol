/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.BlogUser;
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
 * @author stephendowning
 */
public class BlogUserDaoImpl implements BlogUserDao {

    private JdbcTemplate jdbcTemplate;

    public BlogUserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_BLOGUSER
            = "select bu.* from blogUser bu where blogUserId = ?";

    private static final String SQL_SELECT_ALL_BLOGUSERS
            = "select * from blogUser";

    private static final String SQL_SELECT_ACTIVE_USERS
            = SQL_SELECT_ALL_BLOGUSERS + " WHERE enabled = 1";

    private static final String SQL_INSERT_BLOGUSER
            = "insert into blogUser (displayName, loginName, userPassword,"
            + " blogUserId) values (?, ?, ?, ?)";

    private static final String SQL_UPDATE_BLOGUSER
            = "update blogUser set displayName = ?, loginName =?, userPassword =? "
            + "where blogUserId = ?";

    private static final String SQL_DELETE_BLOGUSER
            = "delete from blogUser where blogUserId = ?";

    private static final String SQL_DISABLE_USER
            = "UPDATE blogUser SET enabled = 0 WHERE blogUserId = ?";

    private static final String SQL_INSERT_ROLE
            = "INSERT INTO role (username, role) values (?, ?)";

    private static final String SQL_DELETE_ROLE
            = "DELETE FROM role WHERE username = ?";

    @Override
    public BlogUser getBlogUserById(int blogUserId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BLOGUSER,
                    new BlogUserMapper(), blogUserId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<BlogUser> getAllBlogUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BLOGUSERS,
                new BlogUserMapper());
    }

    // TODO need tests
    @Override
    public List<BlogUser> getActiveBlogUsers() {
        return jdbcTemplate.query(SQL_SELECT_ACTIVE_USERS, new BlogUserMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogUser addBlogUser(BlogUser user) {
        jdbcTemplate.update(SQL_INSERT_BLOGUSER,
                user.getDisplayName(),
                user.getLoginName(),
                user.getPassword(),
                user.getBlogUserId());

        // can't add roles right now, just going to add all user-added users
        // as regular user roles.
//        for (String role : user.getRoles()) {
//            jdbcTemplate.update(SQL_INSERT_ROLE,
//                    user.getLoginName(),
//                    role);
//        }
        jdbcTemplate.update(SQL_INSERT_ROLE, user.getLoginName(), "ROLE_USER");

        int userId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);
        user.setBlogUserId(userId);
        return user;
    }

    @Override
    public void editBlogUser(BlogUser user) {
        jdbcTemplate.update(SQL_UPDATE_BLOGUSER,
                user.getDisplayName(),
                user.getLoginName(),
                user.getPassword(),
                user.getBlogUserId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteBlogUser(int blogUserId) {
        // TODO this is a disable instead of delete. easier that way.
        jdbcTemplate.update(SQL_DISABLE_USER, blogUserId);
    }

    private static final class BlogUserMapper implements RowMapper<BlogUser> {

        @Override
        public BlogUser mapRow(ResultSet rs, int i) throws SQLException {
            BlogUser user = new BlogUser();
            user.setDisplayName(rs.getString("displayName"));
            user.setLoginName(rs.getString("loginName"));
            user.setPassword(rs.getString("userPassword"));
            user.setBlogUserId(rs.getInt("blogUserId"));

//            user.setRoles(roles);
            return user;
        }

    }

}
