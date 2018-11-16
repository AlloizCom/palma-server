package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;

@Dto
public class NewsShortDto <T extends NewsShortDto>{

    protected Long id;
    protected Boolean available;
    protected Timestamp dateTime;
    protected String picturePath;
    protected String keywords;
    protected String description;

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

    public String getKeywords() {
        return keywords;
    }

    public NewsShortDto<T> setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsShortDto<T> setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "NewsShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", dateTime=" + dateTime +
                ", picturePath='" + picturePath + '\'' +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
