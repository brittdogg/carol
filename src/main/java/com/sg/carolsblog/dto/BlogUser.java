/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dto;

import java.util.List;

/**
 *
 * @author trevor
 */
public class BlogUser {
    private int blogUserId;
    private String displayName;
    private String loginName;
    private String password;
    private List<String> roles;

    public int getBlogUserId() {
        return blogUserId;
    }

    public void setBlogUserId(int blogUserId) {
        this.blogUserId = blogUserId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    private void addRole(String role) {
        roles.add(role);
    }
    
}
