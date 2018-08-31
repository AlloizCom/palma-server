package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.dto.NewsByPages;
import com.alloiz.palma.server.dto.NewsShortDto;
import com.alloiz.palma.server.model.News;
import com.alloiz.palma.server.repository.NewsRepository;
import com.alloiz.palma.server.service.NewsService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.service.utils.Validation.*;
import static java.util.stream.Collectors.toList;
import static com.alloiz.palma.server.dto.utils.builder.Builder.map;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private FileBuilder fileBuilder;

    private static final Logger LOGGER = Logger.getLogger(NewsServiceImpl.class);


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

//    @Override
//    public List<Integer> generateRandomArray(int length) {
//        List<Integer> list = new ArrayList<>();
//        int maxIndex = newsRepository.findAllByAvailable(true).size();
//        if(maxIndex >= length) {
//            for (int i = 0; i < length; i++) {
//                while (true){
//                    Integer temp = (int) Math.floor(Math.random() * maxIndex);
//                    if(list.indexOf(temp) == -1){
//                        list.add(temp);
//                        break;
//                    }
//                }
//            }
//        }else
//            list.stream().forEach(elem -> elem = maxIndex);
//
//        LOGGER.info("-----------ARRAY-------" + list);
//        return list;
//    }

//    @Override
//    public List<News> findRandomNews(int amount) {
//        List<Integer> listOfIntegers = getRandomArray(amount);
//        List<News> randomNews = new ArrayList<>();
//        ListIterator<Integer> listIterator = listOfIntegers.listIterator();
//        while (listIterator.hasNext()){
//            LOGGER.info("---Iterator id:" + listIterator.next());
//            randomNews.add(newsRepository
//                    .findOne(
//                            Long.valueOf(listIterator.next())));
//        }
//        LOGGER.info("---List of Random News:" + randomNews);
//        return randomNews;
//    }

    @Override
    public List<News> findRandomNews(int amount) {
        LOGGER.info("---amount:" + amount);
        List<Integer> listOfIntegers = new ArrayList<>();
        List<News> randomNews = new ArrayList<>();
        Boolean runGeneration = true;
//        int greatestId = newsRepository.findAllByAvailable(true).size();
        Long greatestId = newsRepository.returnGreatestId(true);
        LOGGER.info("---greatestId:" + greatestId);
        if (greatestId >= amount){
            while (runGeneration){
                for (int i = 0; i<amount; i++){
                    Integer randomIndex = (int) Math.floor(Math.random() * greatestId);
                    if (checkIdWithBolleanReturnStatement(Long.valueOf(randomIndex))){
                        if (!listOfIntegers.contains(randomIndex)){
                            listOfIntegers.add(randomIndex);
                            LOGGER.info("---Index add:" + randomIndex);
                            if (listOfIntegers.size() == amount){
                                runGeneration = false;
                            }
                        }
                    }
                }
            }
            ListIterator<Integer> listIterator = listOfIntegers.listIterator();
            while (listIterator.hasNext()){
            LOGGER.info("---Iterator id:" + listIterator.next());
            randomNews.add(newsRepository
                    .findOne(
                            Long.valueOf(listIterator.next())));
        }
        }
        LOGGER.info("---List of Random News:" + randomNews);
        return randomNews;
    }


    private List<Integer> getRandomArray (int length){
        List<Integer> list = new ArrayList<>();
        int maxIndex = newsRepository.findAllByAvailable(true).size();
        if(maxIndex >= length) {
            for (int i = 0; i < length; i++) {
                while (true){
                    Integer temp = (int) Math.floor(Math.random() * maxIndex);
                    if(list.indexOf(temp) == -1){
                        list.add(temp);
                        break;
                    }
                }
            }
        }else
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
        return new NewsByPages()
                .setNews(newsRepository.findAllByAvailable(true, pageable).getContent()
                .stream().map(news -> map(news, NewsShortDto.class)).collect(toList()))
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize())
                .setNumberOfPages((newsRepository.countAllByAvailable(true) / pageable.getPageSize()) + 1);
    }
}
