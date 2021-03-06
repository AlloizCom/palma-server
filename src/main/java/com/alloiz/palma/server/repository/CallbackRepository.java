package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Callback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallbackRepository extends JpaRepository<Callback, Long> {

    List<Callback> findAllByAvailable(Boolean available);

    Callback findByAvailableAndId(Boolean available, Long id);

    Page<Callback> findAllByAvailable (Boolean available, Pageable pageable);

    Page<Callback> findAllByAvailableOrderByDateTimeDesc (Boolean available, Pageable pageable);

    Page<Callback> findAll(Pageable pageable);

    Integer countAllByAvailable(Boolean available);
}
