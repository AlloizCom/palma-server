package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.SEO;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;
import java.util.List;

@Dto
public class NewsShortDto <T extends NewsShortDto>{

    protected Long id;
    protected Boolean available;
    protected Timestamp dateTime;
    protected String picturePath;
    protected List<SEODto> seos;

    public NewsShortDto() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateTime() {
        return dateTime;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public T setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
        return (T) this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public T setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return (T) this;
    }

    public List<SEODto> getSeos() {
        return seos;
    }

    public NewsShortDto<T> setSeos(List<SEODto> seos) {
        this.seos = seos;
        return this;
    }

    @Override
    public String toString() {
        return "NewsShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", dateTime=" + dateTime +
                ", picturePath='" + picturePath + '\'' +
                ", seos=" + seos +
                '}';
    }
}
