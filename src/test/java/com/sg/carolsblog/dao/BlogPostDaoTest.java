/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.BlogPost;
import com.sg.carolsblog.dto.Category;
import com.sg.carolsblog.dto.BlogUser;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author bdogg
 */
public class BlogPostDaoTest {

    private final BlogPostDao postDao;
    private final CategoryDao categoryDao;
    private final BlogUserDao userDao;
    BlogPost post1, post2;
    Category cat1, cat2;
    BlogUser user1;
    BlogUser user2;
    LocalDateTime date1, date2, date3, date4;

    public BlogPostDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        postDao = ctx.getBean("postDao", BlogPostDao.class);
        categoryDao = ctx.getBean("categoryDao", CategoryDao.class);
        userDao = ctx.getBean("blogUserDao", BlogUserDao.class);
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        CreateTestData.rebuildDatabase();
        CreateTestData.loadTestData();

        // set up some dates to use
        date1 = LocalDateTime.of(2017, 03, 31, 12, 0);
        date2 = LocalDateTime.of(2017, 04, 1, 11, 30);
        date3 = LocalDateTime.of(2017, 04, 5, 9, 05);
        date4 = LocalDateTime.of(2017, 05, 31, 0, 0);

        // grab objects to be used in blog posts:
        // category
        // user
        cat1 = categoryDao.getCategoryById(1);
        cat2 = categoryDao.getCategoryById(2);

        user1 = userDao.getBlogUserById(1);

        user2 = userDao.getBlogUserById(2);

        // set up some common posts to be used:
        // post with complete information
        // post with no user
        // post with no category
        // post with no publish date
        // are these really dao test cases though? or service?
        // post with all fields
        post1 = new BlogPost();
        post1.setCreatedDate(date1);
        post1.setCreatedBy(user1);
        post1.setModifiedDate(date2);
        post1.setModifiedBy(user2);
        post1.setCategory(cat1);
        post1.setTitle("Blog Title");
        post1.setUrlAlias("the-just-stuff-post");
        post1.setContent("This is just stuff");
        post1.setApproved(true);
        post1.setPublishDate(date3);
        post1.setExpirationDate(date4);

        // post with only required & auto-set fields
        post2 = new BlogPost();
        post2.setCreatedDate(date1);
        post2.setCreatedBy(user1);
        post2.setModifiedDate(date2);
        post2.setModifiedBy(user2);
        post2.setCategory(cat2);
        post2.setTitle("Second Title");
        post2.setUrlAlias("the-second-post");
        post2.setContent("More stuff");
//        post2.setApproved(false);
        post2.setPublishDate(date3);
//        post2.setExpirationDate(null);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddAndGetPostById_AllFields() {

        // add one of the test posts and make sure it got an id
        BlogPost addedPost = postDao.addPost(post1);
        assertNotNull(addedPost.getBlogPostId());

        // get it back out with the id and make sure it matches the above
        BlogPost fromDao = postDao.getPostById(addedPost.getBlogPostId());
        assertEquals(addedPost.getBlogPostId(), fromDao.getBlogPostId());

        // test the post details too
        assertEquals(addedPost.getTitle(), fromDao.getTitle());
        assertEquals(addedPost.getContent(), fromDao.getContent());
        assertEquals(addedPost.getUrlAlias(), fromDao.getUrlAlias());
        assertEquals(addedPost.getCreatedDate(), fromDao.getCreatedDate());
        assertEquals(addedPost.getModifiedDate(), fromDao.getModifiedDate());
        assertEquals(addedPost.getPublishDate(), fromDao.getPublishDate());
        assertEquals(addedPost.getExpirationDate(), fromDao.getExpirationDate());

        // test the nested object ids and details too
        assertEquals(addedPost.getCategory().getCategoryId(),
                fromDao.getCategory().getCategoryId());
        assertEquals(addedPost.getCategory().getCategoryName(),
                fromDao.getCategory().getCategoryName());
        assertEquals(addedPost.getCreatedBy().getBlogUserId(),
                fromDao.getCreatedBy().getBlogUserId());
        assertEquals(addedPost.getCreatedBy().getBlogUserId(),
                fromDao.getCreatedBy().getBlogUserId());
        assertEquals(addedPost.getModifiedBy().getBlogUserId(),
                fromDao.getModifiedBy().getBlogUserId());
        assertEquals(addedPost.getModifiedBy().getBlogUserId(),
                fromDao.getModifiedBy().getBlogUserId());
    }

    @Test
    public void testAddAndGetPostById_RequiredFields() {

        // add one of the test posts and make sure it got an id
        BlogPost addedPost = postDao.addPost(post2);
        assertNotNull(addedPost.getBlogPostId());

        // get it back out with the id and make sure it matches the above
        BlogPost fromDao = postDao.getPostById(addedPost.getBlogPostId());
        assertEquals(addedPost.getBlogPostId(), fromDao.getBlogPostId());

        // test the post details too
        assertEquals(addedPost.getTitle(), fromDao.getTitle());
        assertEquals(addedPost.getContent(), fromDao.getContent());
        assertEquals(addedPost.getUrlAlias(), fromDao.getUrlAlias());
        assertEquals(addedPost.getCreatedDate(), fromDao.getCreatedDate());
        assertEquals(addedPost.getModifiedDate(), fromDao.getModifiedDate());
        assertEquals(addedPost.getPublishDate(), fromDao.getPublishDate());
        assertEquals(addedPost.getExpirationDate(), fromDao.getExpirationDate());

        // test the nested object ids and details too
        assertEquals(addedPost.getCategory().getCategoryId(),
                fromDao.getCategory().getCategoryId());
        assertEquals(addedPost.getCategory().getCategoryName(),
                fromDao.getCategory().getCategoryName());
        assertEquals(addedPost.getCreatedBy().getBlogUserId(),
                fromDao.getCreatedBy().getBlogUserId());
        assertEquals(addedPost.getCreatedBy().getBlogUserId(),
                fromDao.getCreatedBy().getBlogUserId());
        assertEquals(addedPost.getModifiedBy().getBlogUserId(),
                fromDao.getModifiedBy().getBlogUserId());
        assertEquals(addedPost.getModifiedBy().getBlogUserId(),
                fromDao.getModifiedBy().getBlogUserId());
    }

    @Test
    public void getAllPosts_HappyPath() {
        List<BlogPost> postList = postDao.getAllPosts();
        assertEquals(3, postList.size());

        // results should be ordered by publish date descending,
        // so post id #2 should always be first on the list
        BlogPost firstPost = postList.get(0);
        assertEquals(2, firstPost.getBlogPostId());
    }

    @Test
    public void getPostsByCategory_HappyPath() {
        List<BlogPost> postsInCategory = postDao.getPostsByCategory(2);
        assertEquals(2, postsInCategory.size());

        // results should be ordered by publish date descending,
        // so post id #2 should always be first on the list
        BlogPost firstPost = postsInCategory.get(0);
        assertEquals(2, firstPost.getBlogPostId());
    }


    @Test
    public void getPostsByUser_HappyPath() {
        List<BlogPost> postsByUser = postDao.getPostsByUser(2);
        assertEquals(2, postsByUser.size());

        // results should be ordered by publish date descending,
        // so post id #2 should always be first on the list
        BlogPost firstPost = postsByUser.get(0);
        assertEquals(2, firstPost.getBlogPostId());
    }
    
    @Test
    public void getPostByTag_HappyPath() {
        List<BlogPost> postByTag = postDao.getPostsByTag("tagNo2");
        assertTrue(postByTag.size() == 1);
    }

    @Test
    public void editPost_ChangeTitleAndContent() {
        BlogPost post = postDao.getPostById(1);
        post.setTitle("edited title");
        post.setContent("updated content");
        post.setUrlAlias("my-new-alias");
        postDao.editPost(post);
        BlogPost fromDao = postDao.getPostById(1);

        assertEquals(post.getBlogPostId(), fromDao.getBlogPostId());
        assertEquals("edited title", fromDao.getTitle());
        assertEquals("updated content", fromDao.getContent());
        assertEquals("my-new-alias", fromDao.getUrlAlias());
    }

    @Test
    public void editPost_ChangeCategory() {
        BlogPost post = postDao.getPostById(1);
        post.setCategory(cat2);
        postDao.editPost(post);
        BlogPost fromDao = postDao.getPostById(1);

        assertEquals(post.getBlogPostId(), fromDao.getBlogPostId());
        assertEquals(post.getCategory().getCategoryId(),
                fromDao.getCategory().getCategoryId());
        assertEquals(post.getCategory().getCategoryName(),
                fromDao.getCategory().getCategoryName());
    }

    @Test
    public void editPost_ChangePublishDate() {
        BlogPost post = postDao.getPostById(1);
        post.setPublishDate(date4);
        postDao.editPost(post);
        BlogPost fromDao = postDao.getPostById(1);

        assertEquals(post.getBlogPostId(), fromDao.getBlogPostId());
        assertEquals(date4, fromDao.getPublishDate());
    }

    @Test
    public void editPost_ModifiedByAnotherUser() {
        BlogPost post = postDao.getPostById(1);
        // only populating ID because the form only sets the ID for the user.
        BlogUser user = new BlogUser();
        user.setBlogUserId(1);
        post.setModifiedBy(user);
        postDao.editPost(post);
        BlogPost fromDao = postDao.getPostById(1);

        assertEquals(post.getBlogPostId(), fromDao.getBlogPostId());
        assertEquals(1, fromDao.getModifiedBy().getBlogUserId());
    }

    @Test
    public void testDeletePost_PostExists() {
        assertNotNull(postDao.getPostById(1));
        postDao.deletePost(1);
        assertNull(postDao.getPostById(1));
    }

    @Test
    public void deletePost_PostDoesNotExist() {
        assertNull(postDao.getPostById(10));
        try {
            postDao.deletePost(10);
        } catch (Exception e) {
            fail("Exception thrown when deleting a non-existent post.");
        }
    }

//    @Test
//    public void testApprovePost() {
//        post2 = postDao.addPost(post2);
//        postDao.approvePost(post2.getBlogPostId());
//        BlogPost fromDao = postDao.getPostById(post2.getBlogPostId());
//        
//        assertTrue(fromDao.getApproved());
//    }
}
