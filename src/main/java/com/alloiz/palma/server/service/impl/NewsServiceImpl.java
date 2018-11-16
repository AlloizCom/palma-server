package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.dto.NewsByPages;
import com.alloiz.palma.server.dto.NewsFullDto;
import com.alloiz.palma.server.dto.NewsShortDto;
import com.alloiz.palma.server.model.News;
import com.alloiz.palma.server.repository.NewsRepository;
import com.alloiz.palma.server.service.NewsService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static com.alloiz.palma.server.service.utils.Validation.*;
import static java.util.stream.Collectors.toList;


@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger LOGGER = Logger.getLogger(NewsServiceImpl.class);
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
        News news = json(newsJson, News.class);
        LOGGER.info(">>> " + news.getDescription() + " " + news.getKeywords());
        news.getNewsDescriptions()
                .stream()
                .forEach(newsDescription -> newsDescription.setAvailable(true));
        if (multipartFile != null && !multipartFile.isEmpty()) {
            news.setPicturePath(fileBuilder.saveFile(multipartFile));
        }
        return save(news);
    }

    @Override
    public News update(News news) {
        checkObjectExistsById(news.getId(), newsRepository);
        return newsRepository.save(findOne(news.getId())
                .setNewsDescriptions(news.getNewsDescriptions())
                .setAvailable(news.getAvailable())
                .setDescription(news.getDescription())
                .setKeywords(news.getKeywords())
                .setPicturePath(news.getPicturePath()));
    }

    @Override
    public News update(String newsJson, MultipartFile multipartFile) {
        checkJson(newsJson);
        News news = json(newsJson, News.class);
        if (multipartFile != null && !multipartFile.isEmpty()) {
            news.setPicturePath(fileBuilder.saveFile(multipartFile));
        }
        return update(news);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.info("---------------------------News---------------------------");
        LOGGER.info("--------------------------DELETE--------------------------");
        LOGGER.info("--------------------------SERVICE-------------------------");
        LOGGER.info("News ID:" + id);
        LOGGER.info("---------------------------News---------------------------");
        try {
            newsRepository.delete(checkObjectExistsById(id, newsRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Integer> generateRandomArray(int length) {
        List<Integer> list = getRandomArray(length);
        return list;
    }


    @Override
    public List<News> findRandomNews(int amount) {
        if (newsRepository.findAllByAvailable(true).size() >= 6) {
            List<News> randomNews;
            randomNews = newsRepository.findAllByAvailable(true);
            Collections.shuffle(randomNews);
            return randomNews.subList(0, amount);
        } else {
            return newsRepository.findAllByAvailable(true);
        }
    }


    private List<Integer> getRandomArray(int length) {
        List<Integer> list = new ArrayList<>();
        int maxIndex = newsRepository.findAllByAvailable(true).size();
        if (maxIndex >= length) {
            for (int i = 0; i < length; i++) {
                while (true) {
                    Integer temp = (int) Math.floor(Math.random() * maxIndex);
                    if (list.indexOf(temp) == -1) {
                        list.add(temp);
                        break;
                    }
                }
            }
        } else
            list.stream().forEach(elem -> elem = maxIndex);

        LOGGER.info("-----------ARRAY-------" + list);
        return list;
    }

    @Override
    public List<News> findAll(Pageable pageable) {
        List<News> newsList = newsRepository.findAll(pageable).getContent();
        return newsList;
    }

    @Override
    public NewsByPages findAllByAvailable(Pageable pageable) {
        LOGGER.info(">>>" + pageable.getPageNumber());
        LOGGER.info(">>>" + pageable.getPageSize());
        List<NewsFullDto> newsList = newsRepository.findAllByAvailableOrderByDateTimeDesc(true, pageable)
                .getContent()
                .stream()
                .map(news -> map(news, NewsFullDto.class))
                .collect(toList());
        LOGGER.info("-------------News Page---------------");
        newsList.stream().forEach(n -> LOGGER.info(n.getId()));
        LOGGER.info("-----------------------------------");
        newsList.stream().forEach(n -> LOGGER.info(n.getId()));
        return new NewsByPages()
                .setNews(newsList)
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize())
                .setNumberOfPages((newsRepository.countAllByAvailable(true) / pageable.getPageSize()) + 1);

    }
}
