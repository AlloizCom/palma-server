package com.alloiz.palma.server.repository;

import com.alloiz.palma.server.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    List<Proposal> findAllByAvailable(Boolean available);

    Proposal findByAvailableAndId(Boolean available, Long id);

}
