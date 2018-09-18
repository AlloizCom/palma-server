package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.ProposalDescription;

import java.util.List;

@Dto
public class ProposalFullDto extends ProposalShortDto<ProposalFullDto>{

    protected List<ProposalDescription> proposalDescriptions;

    public ProposalFullDto() {
    }

    public List<ProposalDescription> getProposalDescriptions() {
        return proposalDescriptions;
    }

    public ProposalFullDto setProposalDescriptions(List<ProposalDescription> proposalDescriptions) {
        this.proposalDescriptions = proposalDescriptions;
        return this;
    }

    @Override
    public String toString() {
        return "ProposalFullDto{" +
                "proposalDescriptions=" + proposalDescriptions +
                ", id=" + id +
                ", available=" + available +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
