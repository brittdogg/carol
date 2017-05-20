/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.Tag;
import java.util.List;


/**
 *
 * @author trevor
 */
public interface TagDao {
    public Tag getTagById(int tagId);
    public Tag getTagByName(String tagName);
    public List<Tag> getAllTags();
    public Tag addTag(Tag tag);
    public void addAllTags(List<String> tagNames);
    public void editTag(Tag tag);
    public void deleteTag(int tagId);
}
