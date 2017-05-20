/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.service;

import com.sg.carolsblog.dto.Image;
import java.util.List;

/**
 *
 * @author Frederick
 */
public interface ImageService {
    
    public void addImage(Image image);
    
    public void deleteImage(int imageid);
    
    public void editImage(Image image);
    
    public List<Image> getAllImages();
    
    public Image getImageById(int imageid);
    
}
