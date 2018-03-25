/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.controller.admin;

import com.sg.carolsblog.dto.Image;
import com.sg.carolsblog.service.ImageService;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Frederick
 */
@Controller
@RequestMapping({"/image"})
public class ImageController {
    
    ImageService imageService;
    private final String PATH = "/CarolsBlog/image/show?imageid=" ;
    
    public ImageController (ImageService imageService) {
        this.imageService = imageService;
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadImage(
            @RequestParam("userfile") MultipartFile imageFile,
            Model model) {
        
        Image image = new Image();
        
        try {
            Blob imageBlob = new SerialBlob(imageFile.getBytes());
            
            image.setImage(imageBlob);
            image.setContenttype(imageFile.getContentType());
            image.setResult("file_uploaded");
            image.setStatus("ok");
            
            imageService.addImage(image);
            
            image.setFilename(PATH + image.getImageid());
            
            model.addAttribute("image", image);
            
            return "ajax_upload_result";
        } catch (Exception ex) {
            image.setResult("File upload failed: " + ex.getMessage());
            image.setStatus("failed");
            
            model.addAttribute("image", image);
            
            return "ajax_upload_result";
        }
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public void showImage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String parameter = request.getParameter("imageid");
            int imageid = Integer.parseInt(parameter);

            Image image = imageService.getImageById(imageid);
            
            Blob imageBlob = image.getImage();
            int contentLength = (int) imageBlob.length();
                       
            response.setContentType(image.getContenttype());
            response.setContentLength(contentLength);
            response.getOutputStream()
                    .write(imageBlob.getBytes(1, contentLength));

        } catch (SQLException ex) {

        }
    }
    
    @RequestMapping(value = "/blank", method = RequestMethod.GET)
    public String blankImage() {
        return "blank";
    }
}
