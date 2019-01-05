package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class LanguageShortDto<T extends LanguageShortDto> {

    private String languagesName;
    protected Long id;
    protected Boolean available;

    public LanguageShortDto() {
    }

    public String getLanguagesName() {
        return languagesName;
    }

    public T setLanguagesName(String languagesName) {
        this.languagesName = languagesName;
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
