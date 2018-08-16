package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.News;
import com.alloiz.palma.server.repository.NewsRepository;
import com.alloiz.palma.server.service.NewsService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.service.utils.Validation.checkId;
import static com.alloiz.palma.server.service.utils.Validation.checkObjectExistsById;
import static com.alloiz.palma.server.service.utils.Validation.checkSave;
import static com.alloiz.palma.server.service.utils.Validation.checkJson;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public News findOneAvailable(Long id) {
        checkId(id);
        return newsRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<News> findAllAvailable() {
        return newsRepository.findAllByAvailable(true);
    }

    @Override
    public News findOne(Long id) {
        checkId(id);
        return newsRepository.findOne(id);
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News save(News news) {
        checkSave(news);
        return newsRepository.save(news
                .setDateTime(Timestamp.valueOf(LocalDateTime.now()))
                .setAvailable(true));
    }

    @Override
    public News save(String newsJson, MultipartFile multipartFile) {
        checkJson(newsJson);
        News news = json(newsJson,News.class);
        news.getNewsDescriptions().stream().forEach(newsDescription -> newsDescription.setAvailable(true));
        if( multipartFile != null && !multipartFile.isEmpty()){
            news.setPicturePath(fileBuilder.saveFile(multipartFile));
        }
        return save(news);
    }

    @Override
    public News update(News news) {
        checkObjectExistsById(news.getId(), newsRepository);
        return newsRepository.save(findOne(news.getId())
                .setNewsDescriptions(news.getNewsDescriptions())
                .setAvailable(news.getAvailable()));
    }

    @Override
    public News update(String newsJson, MultipartFile multipartFile) {
        checkJson(newsJson);
        News news = json(newsJson, News.class);
        if( multipartFile != null && !multipartFile.isEmpty()){
            news.setPicturePath(fileBuilder.saveFile(multipartFile));
        }
        return update(news);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            newsRepository.delete(checkObjectExistsById(id, newsRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
