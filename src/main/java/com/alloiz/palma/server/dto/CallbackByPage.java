package com.alloiz.palma.server.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Callback DTO for Pageble
 */
public class CallbackByPage {

    private List<CallbackDto> callbacks;
    private Integer currentPage;
    private Integer numberOfPages;
    private Integer numberOfItems;

    public CallbackByPage() {
    }

    public List<CallbackDto> getCallbacks() {
        return callbacks;
    }

    public CallbackByPage setCallbacks(List<CallbackDto> callbacks) {
        this.callbacks = callbacks;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public CallbackByPage setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public CallbackByPage setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public CallbackByPage setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    @Override
    public String toString() {
        return "CallbackByPage{" +
                "callbacks=" + callbacks.stream().map(CallbackDto::getId).collect(toList()) +
                ", currentPage=" + (currentPage == null ? "null" : currentPage) +
                ", numberOfPages=" + (numberOfPages == null ? "null" : numberOfPages) +
                ", numberOfItems=" + (numberOfItems == null ? "null" : numberOfItems) +
                '}';
    }
}
