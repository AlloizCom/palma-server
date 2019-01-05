package com.alloiz.palma.server.repository.payment;

import com.alloiz.palma.server.model.payment.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long>
{
    List<Description> findAllByAvailable(Boolean available);

    Description findByAvailableAndId(Boolean available, Long id);
}
