package com.alloiz.palma.server.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SEO extends BaseEntity<SEO> {


    private String keywords;

    private String description;

    private String language;

    @ManyToOne
    private MainPage mainPage;

    @ManyToOne
    private News news;

    @ManyToOne
    private Room room;

    public String getKeywords() {
        return keywords;
    }

    public SEO setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SEO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public SEO setLanguage(String language) {
        this.language = language;
        return this;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public SEO setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
        return this;
    }

    public News getNews() {
        return news;
    }

    public SEO setNews(News news) {
        this.news = news;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public SEO setRoom(Room room) {
        this.room = room;
        return this;
    }
}
