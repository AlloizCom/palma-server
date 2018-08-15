package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

import java.sql.Timestamp;

@Dto
public class NewsShortDto <T extends NewsShortDto>{

    protected Long id;
    protected Boolean available;
    private Timestamp dateTime;
    private String picturePath;

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

    public Timestamp getDateTime() {
        return dateTime;
    }

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
}
