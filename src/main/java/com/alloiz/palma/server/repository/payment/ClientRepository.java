package com.alloiz.palma.server.repository.payment;

import com.alloiz.palma.server.model.payment.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{

    List<Client> findAllByAvailable(Boolean available);

    Client findByAvailableAndId(Boolean available, Long id);

}
