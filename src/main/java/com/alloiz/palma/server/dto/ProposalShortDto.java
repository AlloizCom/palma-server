package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class ProposalShortDto <T extends ProposalShortDto>{

    protected Long id;
    protected Boolean available;
    protected String picturePath;

    public ProposalShortDto() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T)this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T)this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public T setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return (T)this;
    }

    @Override
    public String toString() {
        return "ProposalShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
