package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.payment.Language;

@Dto
public class MultiLanguageNameFullDto extends MultiLanguageNameShortDto<MultiLanguageNameFullDto>{

    private Language language;

    public MultiLanguageNameFullDto() {
    }

    public Language getLanguage() {
        return language;
    }

    public MultiLanguageNameFullDto setLanguage(Language language) {
        this.language = language;
        return this;
    }
}
