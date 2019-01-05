package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.payment.Language;

@Dto
public class DescriptionFullDto extends DescriptionShortDto<DescriptionFullDto> {

    private Language language;

    public DescriptionFullDto() {
    }

    public Language getLanguage() {
        return language;
    }

    public DescriptionFullDto setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return "DescriptionFullDto{" +
                "language=" + language +
                ", text='" + text + '\'' +
                '}';
    }
}
