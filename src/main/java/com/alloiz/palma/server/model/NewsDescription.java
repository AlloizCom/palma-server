package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Language;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class NewsDescription {

    @ManyToOne(cascade = CascadeType.ALL)
    private News news;
    private Language language;
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    public NewsDescription() {
    }

    public News getNews() {
        return news;
    }

    public NewsDescription setNews(News news) {
        this.news = news;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public NewsDescription setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsDescription setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "NewsDescription{" +
                "news=" + news +
                ", language=" + language +
                ", description='" + description + '\'' +
                '}';
    }
}
