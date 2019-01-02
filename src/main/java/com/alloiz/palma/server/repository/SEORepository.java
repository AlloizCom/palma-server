package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.SEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SEORepository extends JpaRepository<SEO,Long> {

    List<SEO> findAllAvailable(Boolean available);

    List<SEO> findAllByMainPageId(Long id);

    List<SEO> findAllByRoomId(Long id);

    List<SEO> findAllByNewsId(Long id);

    List<SEO> findAllByMainPageIsNotNull();

    List<SEO> findAllByRoomIsNotNull();

    List<SEO> findAllByNewsIsNotNull();

    List<SEO> findAllByMainPageIsNotNullAndAvailable(Boolean available);

    List<SEO> findAllByRoomIsNotNullAndAvailable(Boolean available);

    List<SEO> findAllByNewsIsNotNullAndAvailable(Boolean available);

}
