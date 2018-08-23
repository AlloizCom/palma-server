package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<Image> saveMultiImage(MultipartFile[] multipartFiles);

    Boolean removeImage(List<Image> images, Long imageId);
}
