package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class NewsDescription extends BaseEntity<NewsDescription> {

    //    @ManyToOne(cascade = CascadeType.ALL)
//    private News news;
    @Enumerated(EnumType.STRING)
    private Language language;
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String title;

    public NewsDescription() {
    }

    public String getTitle() {
        return title;
    }

    public NewsDescription setTitle(String title) {
        this.title = title;
        return this;
    }
//
//    public News getNews() {
//        return news;
//    }
//
//    public NewsDescription setNews(News news) {
//        this.news = news;
//        return this;
//    }

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
//                "news=" + (news == null ? "null" : news) +
                ", language=" + (language == null ? "null" : language) +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
