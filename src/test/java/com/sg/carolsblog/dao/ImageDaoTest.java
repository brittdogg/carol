
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import com.sg.carolsblog.dto.Image;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Frederick
 */
public class ImageDaoTest {

    private final ImageDao imageDao;

    public ImageDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");

        imageDao = ctx.getBean("imageDao", ImageDao.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Image> images = imageDao.getAllImages();
        for (Image image : images) {
            imageDao.deleteImage(image.getImageid());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetDeleteImage() {
        try {
            byte[] imageByteArr = "first test picture".getBytes();

            Blob imageBlob = new SerialBlob(imageByteArr);

            Image image = new Image();

            image.setImage(imageBlob);
            image.setContenttype(imageByteArr.getClass().getName());
            image.setResult("file_uploaded");
            image.setStatus("ok");

            imageDao.addImage(image);

            assertNotNull(imageDao.getImageById(image.getImageid()));

            Image fromDao = imageDao.getImageById(image.getImageid());

            //assertTrue(image.getImage().equals(fromDao.getImage()));
            assertEquals(image.getContenttype(), fromDao.getContenttype());
            assertEquals(image.getResult(), fromDao.getResult());
            assertEquals(image.getStatus(), fromDao.getStatus());
            //assertTrue(image.equals(fromDao));

            imageDao.deleteImage(fromDao.getImageid());

            assertNull(imageDao.getImageById(fromDao.getImageid()));
            assertNull(imageDao.getImageById(image.getImageid()));

        } catch (SQLException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testAddGetUpdateImage() {
        try {
            byte[] imageByteArr = "first test picture".getBytes();
            
            Blob imageBlob = new SerialBlob(imageByteArr);
            
            Image image = new Image();
            
            image.setImage(imageBlob);
            image.setContenttype(imageByteArr.getClass().getName());
            image.setResult("file_uploaded");
            image.setStatus("ok");

            imageDao.addImage(image);

            assertNotNull(imageDao.getImageById(image.getImageid()));

            Image fromDao = imageDao.getImageById(image.getImageid());

            //assertTrue(image.getImage().equals(fromDao.getImage()));
            assertEquals(image.getContenttype(), fromDao.getContenttype());
            assertEquals(image.getResult(), fromDao.getResult());
            assertEquals(image.getStatus(), fromDao.getStatus());
            //assertTrue(image.equals(fromDao));
            
            byte[] imageByteArrTwo = "second test picture".getBytes();
            
            Blob imageBlobTwo = new SerialBlob(imageByteArrTwo);
            
            Image imageNew = new Image();
            
            imageNew.setImageid(fromDao.getImageid());
            imageNew.setImage(imageBlobTwo);
            imageNew.setContenttype(imageByteArrTwo.getClass().getName());
            imageNew.setResult("file_uploaded");
            imageNew.setStatus("ok");
            
            imageDao.editImage(imageNew);
            
            Image imageUpdated = imageDao.getImageById(imageNew.getImageid());
            
            assertEquals(imageUpdated.getImageid(),imageNew.getImageid());
            //assertEquals(imageUpdated.getImage(), imageNew.getImage());
            assertEquals(imageUpdated.getContenttype(), imageNew.getContenttype());
            assertEquals(imageUpdated.getResult(), imageNew.getResult());
            assertEquals(imageUpdated.getStatus(), imageNew.getStatus());
            //assertTrue(imageUpdated.equals(imageNew));

        } catch (SQLException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of editImage method, of class ImageDao.
     */
    @Test
    public void testEditImage() {
    }

    /**
     * Test of getAllImages method, of class ImageDao.
     */
    @Test
    public void testGetAllImages() {
    }

    /**
     * Test of getImageById method, of class ImageDao.
     */
    @Test
    public void testGetImageById() {
    }

}
