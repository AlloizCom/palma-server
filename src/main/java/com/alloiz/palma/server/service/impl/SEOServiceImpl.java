package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.SEO;
import com.alloiz.palma.server.repository.SEORepository;
import com.alloiz.palma.server.service.SEOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.checkObjectExistsById;

@Service
public class SEOServiceImpl implements SEOService {

    @Autowired
    private SEORepository seoRepository;

    @Override
    public SEO save(SEO seo) {
        return seoRepository.save(seo);
    }

    @Override
    public List<SEO> findAll() {
        return seoRepository.findAll();
    }

    @Override
    public List<SEO> findAllAvailable() {
        return seoRepository.findAllAvailable(true);
    }

    @Override
    public List<SEO> findAllByMainPageIsNotNull() {
        return seoRepository.findAllByMainPageIsNotNull();
    }

    @Override
    public List<SEO> findAllByRoomIsNotNull() {
        return seoRepository.findAllByRoomIsNotNull();
    }

    @Override
    public List<SEO> findAllByNewsIsNotNull() {
        return seoRepository.findAllByNewsIsNotNull();
    }

    @Override
    public List<SEO> findAllByMainPageIsNotNullAndAvailable(Boolean available) {
        return seoRepository.findAllByMainPageIsNotNullAndAvailable(true);
    }

    @Override
    public List<SEO> findAllByRoomIsNotNullAndAvailable(Boolean available) {
        return seoRepository.findAllByRoomIsNotNullAndAvailable(true);
    }

    @Override
    public List<SEO> findAllByNewsIsNotNullAndAvailable(Boolean available) {
        return seoRepository.findAllByNewsIsNotNullAndAvailable(true);
    }

    @Override
    public List<SEO> findAllByMainPageId(Long id) {
        return seoRepository.findAllByMainPageId(id);
    }

    @Override
    public List<SEO> findAllByRoomId(Long id) {
        return seoRepository.findAllByRoomId(id);
    }

    @Override
    public List<SEO> findAllByNewsId(Long id) {
        return seoRepository.findAllByNewsId(id);
    }

    @Override
    public SEO findOne(Long id) {
        return seoRepository.findOne(id);
    }

    @Override
    public SEO update(SEO seo) {
        return save(
                findOne(seo.getId())
                        .setDescription(seo.getDescription())
                        .setAvailable(seo.getAvailable())
                        .setKeywords(seo.getKeywords())
                        .setLanguage(seo.getLanguage())
                        .setMainPage(seo.getMainPage())
                        .setNews(seo.getNews())
                        .setRoom(seo.getRoom())
        );
    }

    @Override
    public Boolean delete(Long id) {
        try {
            seoRepository.delete(checkObjectExistsById(id, seoRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
