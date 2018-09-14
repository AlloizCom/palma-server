package com.alloiz.palma.server.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Schedule DTO for Pageble
 */
public class ScheduleByPages {

    private List<ScheduleDto> shedules;
    private Integer currentPage;
    private Integer numberOfPages;
    private Integer numberOfItems;

    public ScheduleByPages() {
    }

    public List<ScheduleDto> getShedules() {
        return shedules;
    }

    public ScheduleByPages setShedules(List<ScheduleDto> shedules) {
        this.shedules = shedules;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public ScheduleByPages setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public ScheduleByPages setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public ScheduleByPages setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleByPages{" +
                "shedules=" + shedules.stream().map(ScheduleDto::getId).collect(toList()) +
                ", currentPage=" + (currentPage == null ? "null" : currentPage) +
                ", numberOfPages=" + (numberOfPages == null ? "null" : numberOfPages) +
                ", numberOfItems=" + (numberOfItems == null ? "null" : numberOfItems) +
                '}';
    }
}
