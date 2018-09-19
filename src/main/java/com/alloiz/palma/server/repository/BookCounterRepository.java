package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.BookCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCounterRepository extends JpaRepository<BookCounter,Long> {
}
