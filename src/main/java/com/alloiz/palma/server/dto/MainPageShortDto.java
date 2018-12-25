package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.SEO;

import java.util.List;

@Dto
public class MainPageShortDto <T extends MainPageShortDto>{

    protected Long id;
    protected Boolean available;
    protected List<SEO> seos;

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

    public List<SEO> getSeos() {
        return seos;
    }

    public MainPageShortDto<T> setSeos(List<SEO> seos) {
        this.seos = seos;
        return this;
    }

    @Override
    public String toString() {
        return "MainPageShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", seos=" + seos +
                '}';
    }
}
