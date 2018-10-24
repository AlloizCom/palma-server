package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {

    List<Bin> findAllByAvailable(Boolean available);

    Bin findByAvailableAndId(Boolean available, Long id);

    Bin findByUuid (String uuid);

}
