/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.BlogPostDao;
import com.sg.carolsblog.dao.CommentDao;
import com.sg.carolsblog.dao.PostTagDao;
import com.sg.carolsblog.dao.TagDao;
import com.sg.carolsblog.dto.BlogPost;
import com.sg.carolsblog.dto.Comment;
import com.sg.carolsblog.dto.PostTag;
import com.sg.carolsblog.dto.Tag;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bdogg
 */
public class BlogServiceImpl implements BlogService {

    private final BlogPostDao postDao;
    private final PostTagDao postTagDao;
    private final TagDao tagDao;
    private final CommentDao commentDao;

    public BlogServiceImpl(BlogPostDao postDao, PostTagDao postTagDao, 
            TagDao tagDao, CommentDao commentDao) {
        this.postDao = postDao;
        this.postTagDao = postTagDao;
        this.tagDao = tagDao;
        this.commentDao = commentDao;
    }

    @Override
    public BlogPost getPostById(int postId) {
        return postDao.getPostById(postId);
    }

    @Override
    public List<BlogPost> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public List<BlogPost> getPostsByTag(String tagName) {
        return postDao.getPostsByTag(tagName);
    }

    @Override
    public List<BlogPost> getPostsByCategory(int categoryId) {
        return postDao.getPostsByCategory(categoryId);
    }

    @Override
    public List<BlogPost> getPostsByUser(int userId) {
        return postDao.getPostsByUser(userId);
    }

    @Override
    public BlogPost addPost(BlogPost post) {

        // set created & modified dates to current time
        LocalDateTime createdDate = LocalDateTime.now();
        post.setCreatedDate(createdDate);
        post.setModifiedDate(createdDate);

        // set publish date to current date if not specified
        if (post.getPublishDate() == null) {
            post.setPublishDate(createdDate);
        }

        // set URL alias to a default if not provided by user
        if (post.getUrlAlias() == null || post.getUrlAlias().isEmpty()) {
            post.setUrlAlias(buildUrlAlias(post.getTitle()));
        }
        
        // Replace hashtags with urls in body content
        String bodyContent = post.getContent();
        post.setContent(replaceTagsWithUrls(bodyContent));
        
        // TODO if the user has a certain role, set post.approved to true.
        
        if (hasRequiredFields(post)) {
            BlogPost bPost = postDao.addPost(post);
            // Update the hashtags in the post and update the tags table
            updatePostTags(post);
            
            return bPost;
        } else {
            return null; // how to do error handling here?
        }
    }

    private void updatePostTags(BlogPost post) {
        
        String bodyContent = post.getContent();
        // Get hashtags from the post and add them to the tags table
        List<String> tags = getHashTags(bodyContent);
        
        // Replace hashtags with urls in body content
        post.setContent(replaceTagsWithUrls(bodyContent));

        // Add new tags to tags table
        tagDao.addAllTags(tags);
        
        // Add post tag relationship to postTag table
        for(String tagName : tags) {
            Tag tag = tagDao.getTagByName(tagName);
            
            PostTag postTag = postTagDao.getPostTagByRelationship(
                    post.getBlogPostId(), tag.getTagId());
            
            
            // Only add the postTag if it doesn't already exist
            if( postTag == null){
                postTag = new PostTag();
                postTag.setBlogPost(post);
                postTag.setTag(tag);
                postTagDao.addPostTag(postTag);
            }
            
        }
        
    }

    @Override
    public void editPost(BlogPost post) {
        
        post.setModifiedDate(LocalDateTime.now());
        
        // set url alias to a default if not provided by user
        if (post.getUrlAlias() == null || post.getUrlAlias().isEmpty()) {
            post.setUrlAlias(buildUrlAlias(post.getTitle()));
        }
        
        // Update the hashtags in the post and update the tags table
        updatePostTags(post);

        if (hasRequiredFields(post)) {
            postDao.editPost(post);
        }
    }

    @Override
    public void deletePost(int postId) {
        // if there are any tags on this post,
        // delete all the post-tag relationships first.
        List<PostTag> tagsForThisPost = postTagDao.getTagsForPost(postId);
        if (!tagsForThisPost.isEmpty()) {
            for (PostTag pt : tagsForThisPost) {
                postTagDao.deletePostTag(pt.getPostTagId());
                
                // if there are no more posts associated with a tag
                // after this relationship has been removed,
                // delete the orphan tag too.
                List<PostTag> postsLeftOnThisTag
                        = postTagDao.getPostsForTag(pt.getTag().getTagId());
                if (postsLeftOnThisTag.isEmpty()) {
                    tagDao.deleteTag(pt.getTag().getTagId());
                }
            }
        }
        
        //delete comments for this post
        List<Comment> commentsForThisPost = commentDao.getCommentsByPost(postId);
        for (Comment c : commentsForThisPost) {
            commentDao.deleteComment(c.getCommentId());
        }
        
        
        postDao.deletePost(postId);
    }

    @Override
    public void approvePost(int postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Get post by tag name
    
    // Get tags from post content
    private List<String> getHashTags(String input) {
        
        final String hashTagRegEx = "(?<=^#|\\s#|;#|>#)([A-Za-z_]+\\w*)";
        
        List<String> hashTags = new ArrayList<>();
        Pattern pattern = Pattern.compile(hashTagRegEx);
        Matcher m = pattern.matcher(input);
        while (m.find()) {
            hashTags.add(m.group(1));
        }
        return hashTags;
    }
    
    // Replace tags with urls
    private String replaceTagsWithUrls(String input) {
        List<String> hashTags = getHashTags(input);
        String outPut = null;
        String regEx;
        for(String tag : hashTags) {
            regEx = "(?<=^|\\s|;|>)#" + tag + "(?!\\w)";
            input = input.replaceAll(regEx, "<a href=\"/CarolsBlog/blog/tag/" + tag + "\">#" + tag + "</a>");
        }
        return input;
    }
    
    // Build url alias from title
    private String buildUrlAlias(String title) {
        return title.replaceAll("\\s+", "-").toLowerCase();
    }

    private boolean hasRequiredFields(BlogPost post) {

        if (post.getTitle() == null
                || post.getContent() == null
                || post.getUrlAlias() == null
                || post.getCategory() == null
                || post.getCreatedBy() == null
                || post.getCreatedDate() == null
                || post.getModifiedBy() == null
                || post.getModifiedDate() == null
                || post.getPublishDate() == null) {
            return false;
        } else {
            return true;
        }
    }

}
