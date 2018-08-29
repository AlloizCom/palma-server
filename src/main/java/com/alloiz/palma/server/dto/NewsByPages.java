package com.alloiz.palma.server.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class NewsByPages {

    private List<NewsShortDto> news;
    private Integer currentPage;
    private Integer numberOfPages;
    private Integer numberOfItems;

    public NewsByPages() {
    }

    public List<NewsShortDto> getNews() {
        return news;
    }

    public NewsByPages setNews(List<NewsShortDto> news) {
        this.news = news;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public NewsByPages setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public NewsByPages setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public NewsByPages setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    @Override
    public String toString() {
        return "NewsByPages{" +
                "news=" + news.stream().map(NewsShortDto::getId).collect(toList()) +
                ", currentPage=" + (currentPage == null ? "null" : currentPage) +
                ", numberOfPages=" + (numberOfPages == null ? "null" : numberOfPages) +
                ", numberOfItems=" + (numberOfItems == null ? "null" : numberOfItems) +
                '}';
    }
}
