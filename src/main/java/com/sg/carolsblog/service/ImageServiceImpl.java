/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dao.ImageDao;
import com.sg.carolsblog.dao.ImageDaoImpl;
import com.sg.carolsblog.dto.Image;
import java.util.List;

/**
 *
 * @author Frederick
 */
public class ImageServiceImpl implements ImageService{
    
    ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
    
    @Override
    public void addImage(Image image) {
        imageDao.addImage(image);
    }

    @Override
    public void deleteImage(int imageid) {
        imageDao.deleteImage(imageid);
    }

    @Override
    public void editImage(Image image) {
        imageDao.editImage(image);
    }

    @Override
    public List<Image> getAllImages() {
        return imageDao.getAllImages();
    }

    @Override
    public Image getImageById(int imageid) {
        return imageDao.getImageById(imageid);
    }
    
}
