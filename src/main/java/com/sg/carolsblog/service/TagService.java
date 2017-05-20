/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dto.Tag;
import java.util.List;

/**
 *
 * @author stephendowning
 */
public interface TagService {
    
    public Tag getTagById(int tagId);
    public List<Tag> getAllTags();
    public Tag addTag(Tag tag);
    public void editTag(Tag tag);
    public void deleteTag(int tagId);
    
}
