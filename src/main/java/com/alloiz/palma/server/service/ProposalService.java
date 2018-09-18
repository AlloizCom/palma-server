package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Proposal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProposalService {

    Proposal findOneAvailable(Long id);

    List<Proposal> findAllAvailable();

    Proposal findOne(Long id);

    List<Proposal> findAll();

    Proposal save(Proposal proposal);

    Proposal save(String proposalJson);

    Proposal save(String proposalJson, MultipartFile multipartFile);

    Proposal update(String proposalJson, MultipartFile multipartFile);

    Proposal update(Proposal proposal);

    Proposal update(String proposalJson);

    Boolean delete(Long id);
}
