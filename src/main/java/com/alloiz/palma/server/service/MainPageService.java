package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.MainPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MainPageService {

    MainPage findOneAvailable(Long id);

    List<MainPage> findAllAvailable();

    MainPage findOne(Long id);

    List<MainPage> findAll();

    MainPage save(MainPage room);

    MainPage save(String mainPageJson,MultipartFile[] multipartFiles);

    MainPage update(MainPage room);

    MainPage update(String mainPageJson, MultipartFile[] multipartFiles);

    MainPage update(String mainPageJson);

    MainPage addImages(Long mainPageId, MultipartFile[] multipartFiles);

    Boolean delete(Long id);

    Boolean deleteImage(Long mainPageId, Long imageId);

}
