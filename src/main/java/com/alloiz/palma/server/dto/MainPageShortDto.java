package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class MainPageShortDto <T extends MainPageShortDto>{

    protected Long id;
    protected Boolean available;

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

    @Override
    public String toString() {
        return "MainPageShortDto{" +
                "id=" + id +
                ", available=" + available +
                '}';
    }
}
