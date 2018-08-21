package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Image;
import com.alloiz.palma.server.model.MainPage;
import com.alloiz.palma.server.repository.ImageRepository;
import com.alloiz.palma.server.repository.MainPageRepository;
import com.alloiz.palma.server.service.MainPageService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    private MainPageRepository mainPageRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public MainPage findOneAvailable(Long id) {
        checkId(id);
        return mainPageRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<MainPage> findAllAvailable() {
        return mainPageRepository.findAllByAvailable(true);
    }

    @Override
    public MainPage findOne(Long id) {
        checkId(id);
        return mainPageRepository.findOne(id);
    }

    @Override
    public List<MainPage> findAll() {
        return mainPageRepository.findAll();
    }

    @Override
    public MainPage save(MainPage mainPage) {
        checkSave(mainPage);
        mainPageRepository.save(mainPage.setAvailable(true));
        return mainPage;
    }

    @Override
    public MainPage save(String mainPageJson, MultipartFile[] multipartFiles) {
        checkJson(mainPageJson);
        MainPage mainPage = json(mainPageJson, MainPage.class);
        if (multipartFiles != null && multipartFiles.length != 0) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                Image image = new Image();
                image.setPath(fileBuilder.saveFile(multipartFile)).setAvailable(true);
                images.add(image);
            }
            mainPage.setImages(images);
        }
        return save(mainPage);
    }

    @Override
    public MainPage update(MainPage mainPage) {
        checkObjectExistsById(mainPage.getId(), mainPageRepository);
        return mainPageRepository.save(findOne(mainPage.getId())
                .setAvailable(mainPage.getAvailable()));
    }

    @Override
    public MainPage update(String mainPageJson, MultipartFile[] multipartFiles) {
        checkJson(mainPageJson);
        MainPage mainPage = json(mainPageJson, MainPage.class);
        checkObjectExistsById(mainPage.getId(), mainPageRepository);
        if (multipartFiles != null && multipartFiles.length != 0) {
            List<Image> images = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                Image image = new Image();
                image.setPath(fileBuilder.saveFile(multipartFile)).setAvailable(true);
                images.add(image);
            }
            mainPage.setImages(images);
        }
        return mainPageRepository.save(findOne(mainPage.getId())
                .setAvailable(mainPage.getAvailable()));
    }

    @Override
    public MainPage update(String mainPageJson) {
        checkJson(mainPageJson);
        MainPage mainPage = json(mainPageJson, MainPage.class);
        return update(mainPage);
    }

    @Override
    public MainPage addImages(Long mainPageId, MultipartFile[] multipartFiles) {
        MainPage mainPage = findOne(mainPageId);
        List<Image> imageList = mainPage.getImages();
        for (MultipartFile file : multipartFiles) {
            Image image = new Image().setPath(fileBuilder.saveFile(file)).setAvailable(true);
            imageRepository.save(image);
            imageList.add(image);
        }
        return mainPageRepository.save(mainPage);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            mainPageRepository.delete(checkObjectExistsById(id, mainPageRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteImage(Long mainPageId, Long imageId) {
        checkId(imageId);
        MainPage mainPage = findOne(mainPageId);
        List<Image> images = mainPage.getImages();
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
