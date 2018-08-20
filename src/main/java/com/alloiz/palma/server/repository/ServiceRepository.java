package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findAllByAvailable(Boolean available);

    Service findByAvailableAndId(Boolean available, Long id);
}
