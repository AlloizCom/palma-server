package com.alloiz.palma.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Proposal extends BaseEntity<Proposal>{

    private String picturePath;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposal_id")
    private List<ServiceDescription> proposalDescriptions;

    public Proposal() {
    }

    public String getPicturePath() {
        return picturePath;
    }

    public Proposal setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    public List<ServiceDescription> getProposalDescriptions() {
        return proposalDescriptions;
    }

    public Proposal setProposalDescriptions(List<ServiceDescription> proposalDescriptions) {
        this.proposalDescriptions = proposalDescriptions;
        return this;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "picturePath='" + picturePath + '\'' +
                ", proposalDescriptions=" +
                (proposalDescriptions == null ? "null" : proposalDescriptions) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
