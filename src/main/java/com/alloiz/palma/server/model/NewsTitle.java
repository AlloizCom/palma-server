package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Language;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class NewsTitle extends BaseEntity<NewsTitle>{

    @ManyToOne(cascade = CascadeType.ALL)
    private News news;
    private Language language;

    private String title;

    public NewsTitle() {
    }

    public News getNews() {
        return news;
    }

    public NewsTitle setNews(News news) {
        this.news = news;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public NewsTitle setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsTitle setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return "NewsTitle{" +
                "news=" + news +
                ", language=" + language +
                ", title='" + title + '\'' +
                '}';
    }
}
