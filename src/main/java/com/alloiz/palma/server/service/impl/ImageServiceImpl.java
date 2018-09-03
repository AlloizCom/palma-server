package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Image;
import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.repository.ImageRepository;
import com.alloiz.palma.server.service.ImageService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = Logger.getLogger(ImageServiceImpl.class);

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> saveMultiImage (MultipartFile[] multipartFiles){
        return Arrays.stream(multipartFiles)
                .map(multipartFile ->
                new Image().setPath(fileBuilder.saveFile(multipartFile))
                .setAvailable(true)).collect(toList());
    }

    @Override
    public Boolean removeImage(List<Image> images, Long imageId){
        LOGGER.info("ImageId - " + imageId );
        LOGGER.info("ImageID Type" + imageId.getClass().toString());
        ListIterator<Image> imageListIterator = images.listIterator();
        while (imageListIterator.hasNext()) {
            if (imageListIterator.next().getId().equals(imageId)) {
                LOGGER.info("SUKA IBANYI abra kedavra");
                imageListIterator.remove();
                imageRepository.delete(imageId);
                return true;
            }
        }
        return false;
    }

}
