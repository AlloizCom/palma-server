package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class MainPageShortDto <T extends MainPageShortDto>{

    protected Long id;
    protected Boolean available;
    protected String keywords;
    protected String description;

    public MainPageShortDto() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    public String getKeywords() {
        return keywords;
    }

    public MainPageShortDto<T> setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MainPageShortDto<T> setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "MainPageShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
