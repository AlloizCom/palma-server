package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByAvailable(Boolean available);

    News findByAvailableAndId(Boolean available, Long id);

    Page<News> findAllByAvailable(Boolean available, Pageable pageable);

    Page<News> findAll(Pageable pageable);

    Integer countAllByAvailable(Boolean available);

    @Query("select max (n.id) from News n")
    Long returnGreatestId(Boolean get);

    @Query("select min(n.id) from News n")
    Long returnLovestId(Boolean get);
}
