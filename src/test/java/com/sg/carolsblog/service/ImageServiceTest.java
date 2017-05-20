/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dto.Image;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Frederick
 */
public class ImageServiceTest {
    
    private final ImageService imageService;
    
    public ImageServiceTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        
        imageService = ctx.getBean("imageService", ImageService.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addImage method, of class ImageService.
     */
    @Test
    public void testAddImage() {
    }

    /**
     * Test of deleteImage method, of class ImageService.
     */
    @Test
    public void testDeleteImage() {
    }

    /**
     * Test of editImage method, of class ImageService.
     */
    @Test
    public void testEditImage() {
    }

    /**
     * Test of getAllImages method, of class ImageService.
     */
    @Test
    public void testGetAllImages() {
    }

    /**
     * Test of getImageById method, of class ImageService.
     */
    @Test
    public void testGetImageById() {
    }

    public class ImageServiceImpl implements ImageService {

        public void addImage(Image image) {
        }

        public void deleteImage(int imageid) {
        }

        public void editImage(Image image) {
        }

        public List<Image> getAllImages() {
            return null;
        }

        public Image getImageById(int imageid) {
            return null;
        }
    }
    
}
