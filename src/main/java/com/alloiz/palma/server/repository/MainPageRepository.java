package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.MainPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainPageRepository extends JpaRepository<MainPage, Long> {

    List<MainPage> findAllByAvailable(Boolean available);

    MainPage findByAvailableAndId(Boolean available, Long id);
}
