package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.News;

import java.util.List;

public interface NewsService {

    News findOneAvailable(Long id);

    List<News> findAllAvailable();

    News findOne(Long id);

    List<News> findAll();

    News save(News news);

    News update(News update);

    Boolean delete(Long id);

}
