package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Proposal;
import com.alloiz.palma.server.repository.ProposalRepository;
import com.alloiz.palma.server.service.ProposalService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.service.utils.Validation.*;

/**
 * Service implementation for proposal on the main page
 */
@Service
public class ProposalServiceImpl implements ProposalService {

    private static final Logger LOGGER = Logger.getLogger(ProposalServiceImpl.class);

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public Proposal findOneAvailable(Long id) {
        checkId(id);
        return proposalRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Proposal> findAllAvailable() {
        return proposalRepository.findAllByAvailable(true);
    }

    @Override
    public Proposal findOne(Long id) {
        checkId(id);
        return proposalRepository.findOne(id);
    }

    @Override
    public List<Proposal> findAll() {
        return proposalRepository.findAll();
    }

    @Override
    public Proposal save(Proposal proposal) {
        checkSave(proposal);
        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal save(String proposalJson) {
        checkJson(proposalJson);
        Proposal proposal = json(proposalJson, Proposal.class);
        proposal.getProposalDescriptions()
                .stream()
                .forEach(proposalDescription -> proposalDescription.setAvailable(true));
        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal save(String proposalJson, MultipartFile multipartFile) {
        checkJson(proposalJson);
        Proposal proposal = json(proposalJson, Proposal.class);
        LOGGER.info("----PROPOSAL--SAVE----");
        LOGGER.info(proposal);
        proposal.getProposalDescriptions()
                .stream()
                .forEach(proposalDescription -> proposalDescription.setAvailable(true));
        if (multipartFile != null && !multipartFile.isEmpty()) {
            proposal.setPicturePath(fileBuilder.saveFile(multipartFile));
        }
        return save(proposal);
    }

    @Override
    public Proposal update(String proposalJson, MultipartFile multipartFile) {
        checkJson(proposalJson);
        Proposal proposal = json(proposalJson, Proposal.class);
        checkObjectExistsById(proposal.getId(), proposalRepository);
        if (multipartFile != null && !multipartFile.isEmpty())
            proposal.setPicturePath(fileBuilder.saveFile(multipartFile));
        return save(proposal)
                .setAvailable(proposal.getAvailable()
                );
    }

    @Override
    public Proposal update(Proposal proposal) {
        checkObjectExistsById(proposal.getId(), proposalRepository);
        return proposalRepository.save(findOne(proposal.getId())
                .setProposalDescriptions(proposal.getProposalDescriptions())
                .setAvailable(proposal.getAvailable()));
    }

    @Override
    public Proposal update(String proposalJson) {
        checkJson(proposalJson);
        Proposal proposal = json(proposalJson, Proposal.class);
        return update(proposal);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            proposalRepository.delete(checkObjectExistsById(id, proposalRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
