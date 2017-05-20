/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.PostTag;
import java.util.List;

/**
 *
 * @author trevor
 */
public interface PostTagDao {

    public PostTag getPostTagById(int id);
    
    public PostTag getPostTagByRelationship(int postId, int tagId);

    public PostTag addPostTag(PostTag postTag);

    public void editPostTag(PostTag postTag);

    public void deletePostTag(int id);

    public List<PostTag> getPostsForTag(int tagId);

    public List<PostTag> getTagsForPost(int postId);
}
