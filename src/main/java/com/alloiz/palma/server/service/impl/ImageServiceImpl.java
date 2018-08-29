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
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ImageServiceImpl implements ImageService {

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
