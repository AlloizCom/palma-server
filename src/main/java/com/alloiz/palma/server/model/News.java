package com.alloiz.palma.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;

@Entity
public class News extends BaseEntity<UserEntity> {


    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private NewsDescription newsDescription;

    private Timestamp datetime;
    private String picturePath;

    public News() {
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

    public NewsDescription getNewsDescription() {
        return newsDescription;
    }

    public News setNewsDescription(NewsDescription newsDescription) {
        this.newsDescription = newsDescription;
        return this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public News setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsDescription=" + newsDescription +
                ", datetime=" + datetime +
                ", picturePath='" + picturePath + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
