package com.alloiz.palma.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class News extends BaseEntity<UserEntity>{

    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String text;
    private Timestamp datetime;
    private String picturePath;

    public News() {
    }

    public String getTitle() {
        return title;
    }

    public News setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public News setText(String text) {
        this.text = text;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    public News setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public News setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }
}
