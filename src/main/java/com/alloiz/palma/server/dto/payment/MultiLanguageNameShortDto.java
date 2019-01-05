package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class MultiLanguageNameShortDto<T extends MultiLanguageNameShortDto<T>>{

    private String name;
    protected Long id;
    protected Boolean available;

    public MultiLanguageNameShortDto() {
    }

    public String getName() {
        return name;
    }

    public T setName(String name) {
        this.name = name;
        return (T)this;
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
}
