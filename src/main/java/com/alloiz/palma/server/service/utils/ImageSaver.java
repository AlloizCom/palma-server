package com.alloiz.palma.server.service.utils;

import com.alloiz.palma.server.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ImageSaver {

    @Autowired
    private static FileBuilder fileBuilder;

    public static List<Image> saveMultiImage (MultipartFile[] multipartFiles){
        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            Image image = new Image();
            image.setPath(fileBuilder.saveFile(multipartFile)).setAvailable(true);
            images.add(image);
        }
        return images;
    }

}
