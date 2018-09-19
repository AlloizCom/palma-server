package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.BookCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCounterRepository extends JpaRepository<BookCounter, Long> {
    List<BookCounter> findAllByAvailable(Boolean available);
}
