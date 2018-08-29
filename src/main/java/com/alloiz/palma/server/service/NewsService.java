package com.alloiz.palma.server.service;

import com.alloiz.palma.server.dto.NewsByPages;
import com.alloiz.palma.server.model.News;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {

    News findOneAvailable(Long id);

    List<News> findAllAvailable();

    News findOne(Long id);

    List<News> findAll();

    News save(News news);

    News save(String newsJson, MultipartFile multipartFile);

    News update(News update);

    News update(String newsJson, MultipartFile multipartFile);

    Boolean delete(Long id);

    List<Integer> generateRandomArray(int length);

    List<News> findAll(Pageable pageable);

    NewsByPages findAllByAvailable(Pageable pageable);

}
