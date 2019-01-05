package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class DescriptionShortDto <T extends DescriptionShortDto>{

    protected String text;

    public DescriptionShortDto() {
    }

    public String getText() {
        return text;
    }

    public T setText(String text) {
        this.text = text;
        return (T) this;
    }


    @Override
    public String toString() {
        return "DescriptionShortDto{" +
                "text='" + text + '\'' +
                '}';
    }
}
