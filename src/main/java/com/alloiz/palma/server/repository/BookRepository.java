package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAvailable(Boolean available);

    Book findByAvailableAndId(Boolean available, Long id);

    //Book findByUuid(String uuid);

    /**
     * For pageable
     * @param available
     * @param pageable
     * @return
     */
    Page<Book> findAllByAvailable(Boolean available, Pageable pageable);

    Page<Book> findAll(Pageable pageable);

    Integer countAllByAvailable(Boolean available);
}
