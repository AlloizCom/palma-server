package com.alloiz.palma.server.repository.payment;

import com.alloiz.palma.server.model.payment.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long>
{
}
