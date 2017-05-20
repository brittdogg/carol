/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.TagDao;
import com.sg.carolsblog.dto.Tag;
import java.util.List;

/**
 *
 * @author stephendowning
 */
public class TagServiceImpl implements TagService{
    
    TagDao tagDao;
    
    public TagServiceImpl (TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag getTagById(int tagId) {
        return tagDao.getTagById(tagId);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
   }

    @Override
    public Tag addTag(Tag tag) {
        return tagDao.addTag(tag);
   }

    @Override
    public void editTag(Tag tag) {
        tagDao.editTag(tag);
   }

    @Override
    public void deleteTag(int tagId) {
        tagDao.deleteTag(tagId);
   }
    
}
