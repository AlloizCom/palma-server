package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.CallbackCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallbackCounterRepository extends JpaRepository<CallbackCounter, Long> {
    List<CallbackCounter> findAllByAvailable(Boolean available);
}
