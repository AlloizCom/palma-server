package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Image;
import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.repository.ImageRepository;
import com.alloiz.palma.server.service.ImageService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private static FileBuilder fileBuilder;

    @Autowired
    private static ImageRepository imageRepository;

    @Override
    public List<Image> saveMultiImage (MultipartFile[] multipartFiles){
        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            Image image = new Image();
            image.setPath(fileBuilder.saveFile(multipartFile)).setAvailable(true);
            images.add(image);
        }
        return images;
    }

    @Override
    public Boolean removeImage(List<Image> images, Long imageId){
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
