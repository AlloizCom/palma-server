package com.alloiz.palma.server.service.utils;

import com.alloiz.palma.server.model.Image;
import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ImageSaver {

    @Autowired
    private static FileBuilder fileBuilder;

    @Autowired
    private static ImageRepository imageRepository;

    public static List<Image> saveMultiImage (MultipartFile[] multipartFiles){
        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            Image image = new Image();
            image.setPath(fileBuilder.saveFile(multipartFile)).setAvailable(true);
            images.add(image);
        }
        return images;
    }

    public static Boolean removeImage(List<Image> images, Long imageId){
        ListIterator<Image> imageListIterator = images.listIterator();
        while (imageListIterator.hasNext()) {
            if (imageListIterator.next().getId().equals(imageId)) {
                imageListIterator.remove();
                imageRepository.delete(imageId);
                return true;
            }
        }
        return false;
    }

}
