/*
 * $Id: $
 * $Revision: $
 * $Author: $
 * $Date: $
 * Copyright (c) 2018 Trustwave Holdings, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of Trustwave Holdings, Inc.  Use of this software is governed by
 * the terms and conditions of the license statement and limited
 * warranty furnished with the software.
 *
 * IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD TRUSTWAVE HOLDINGS INC.,
 * ITS RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST
 * ANY CLAIMS OR LIABILITIES ARISING OUT OF OR RESULTING FROM THE USE,
 * MODIFICATION, OR DISTRIBUTION OF PROGRAMS OR FILES CREATED FROM,
 * BASED ON, AND/OR DERIVED FROM THIS SOURCE CODE FILE.
 */
package com.sg.carolsblog.controller.rest;

import com.sg.carolsblog.controller.rest.exception.ResourceNotFoundException;
import com.sg.carolsblog.dto.BlogUser;
import com.sg.carolsblog.service.BlogUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * --- TODO: Class comments go here ---
 *
 * <b><pre>
 * Copyright (c) 2018 Trustwave Holdings, Inc.
 * All rights reserved.
 * </pre></b>
 *
 * @author bscaccia
 * @version $Revision: $
 */
@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    private BlogUserService _userService;

    @Inject
    public UserRestController(BlogUserService userService) {
        _userService = userService;
    }

    // this is the default method for the parent request mapping + GET
    @GetMapping
    public List<BlogUser> findAll() {
        return _userService.getActiveBlogUsers();
    }

    @GetMapping(value = "/{id}")
    public BlogUser findOne(@PathVariable("id") int id) {
        return _userService.getBlogUserById(id);
    }

    // same uri as parent request mapping but with a POST instead of a GET
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody BlogUser newUser) {
        return _userService.addBlogUser(newUser).getBlogUserId();
    }

    @PutMapping
    public void update(@RequestBody BlogUser user) {
        if (_userService.getBlogUserById(user.getBlogUserId()) != null) {
            _userService.editBlogUser(user);
        } else {
            throw new ResourceNotFoundException("The user does not exist");
        }
    }

    @DeleteMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK) // this is the default
    public void delete(@PathVariable("id") int userId) {
        if (_userService.getBlogUserById(userId) != null) {
            _userService.deleteBlogUser(userId);
        } else {
            throw new ResourceNotFoundException("The user does not exist");
        }
    }

}
