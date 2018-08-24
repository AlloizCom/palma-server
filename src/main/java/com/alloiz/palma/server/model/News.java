package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
//import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
//import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class News extends BaseEntity<News> {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "news_description_id")
    private List<NewsDescription> newsDescriptions;

    private Timestamp dateTime;
    private String picturePath;

    public News() {
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateTime() {
        return dateTime;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public News setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public List<NewsDescription> getNewsDescriptions() {
        return newsDescriptions;
    }

    public News setNewsDescriptions(List<NewsDescription> newsDescriptions) {
        this.newsDescriptions = newsDescriptions;
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
                "newsDescription=" + newsDescriptions +
                ", dateTime=" + dateTime +
                ", picturePath='" + picturePath + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
