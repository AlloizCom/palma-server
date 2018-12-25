package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.SEO;

import java.util.List;

public interface SEOService {

    SEO save(SEO seo);

    List<SEO> findAll();

    List<SEO> findAllAvailable();

    List<SEO> findAllByMainPageIsNotNull();

    List<SEO> findAllByRoomIsNotNull();

    List<SEO> findAllByNewsIsNotNull();

    List<SEO> findAllByMainPageIsNotNullAndAvailable(Boolean available);

    List<SEO> findAllByRoomIsNotNullAndAvailable(Boolean available);

    List<SEO> findAllByNewsIsNotNullAndAvailable(Boolean available);

    List<SEO> findAllByMainPageId(Long id);

    List<SEO> findAllByRoomId(Long id);

    List<SEO> findAllByNewsId(Long id);

    SEO findOne(Long id);

    SEO update(SEO seo);

    Boolean delete(Long id);

}
