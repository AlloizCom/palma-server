package com.alloiz.palma.server.dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Book DTO for Pageable
 */
public class BookByPage {

    private List<BookDto> bookDtoList;
    private Integer currentPage;
    private Integer numberOfPages;
    private Integer numberOfItems;

    public BookByPage() {
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public BookByPage setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public BookByPage setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public BookByPage setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public BookByPage setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    @Override
    public String toString() {
        return "BookByPage{" +
                "bookDtoList=" + bookDtoList.stream().map(BookDto::getId).collect(toList()) +
                ", currentPage=" + (currentPage == null ? "null" : currentPage) +
                ", numberOfPages=" + (numberOfPages == null ? "null" : numberOfPages) +
                ", numberOfItems=" + (numberOfItems == null ? "null" : numberOfItems) +
                '}';
    }
}
