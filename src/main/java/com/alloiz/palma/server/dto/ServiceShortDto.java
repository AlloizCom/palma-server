package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class ServiceShortDto <T extends ServiceShortDto>{

    protected Long id;
    protected Boolean available;
    protected Boolean showOnTop;
    protected String name;
    protected String picturePath;

    public ServiceShortDto() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T)this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T)this;
    }

    public String getName() {
        return name;
    }

    public T setName(String name) {
        this.name = name;
        return (T)this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public T setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return (T)this;
    }

    public Boolean getShowOnTop() {
        return showOnTop;
    }

    public T setShowOnTop(Boolean showOnTop) {
        this.showOnTop = showOnTop;
        return (T)this;
    }

    @Override
    public String toString() {
        return "ServiceShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", name='" + name + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
