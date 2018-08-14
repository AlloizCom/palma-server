package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.NewsDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDescriptionRepository extends JpaRepository<NewsDescription, Long> {

    List<NewsDescription> findAllByAvailable(Boolean available);

    NewsDescription findByAvailableAndId(Boolean available, Long id);
}
