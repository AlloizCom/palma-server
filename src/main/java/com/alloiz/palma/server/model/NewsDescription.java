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

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String headerText;

    @Column(columnDefinition = "LONGTEXT")
    private String mainText;

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

    public String getHeaderText() {
        return headerText;
    }

    public NewsDescription setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    public String getMainText() {
        return mainText;
    }

    public NewsDescription setMainText(String mainText) {
        this.mainText = mainText;
        return this;
    }

    @Override
    public String toString() {
        return "NewsDescription{" +
                ", language=" + (language == null ? "null" : language) +                ", title='" + title + '\'' +
                ", headerText='" + headerText + '\'' +
                ", mainText='" + mainText + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
