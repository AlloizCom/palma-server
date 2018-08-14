package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByAvailable(Boolean available);

    News findByAvailableAndId(Boolean available, Long id);
}
