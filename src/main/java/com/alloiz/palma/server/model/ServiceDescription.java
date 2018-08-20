package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class ServiceDescription extends BaseEntity<ServiceDescription>{

    @Enumerated(EnumType.STRING)
    private Language language;

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String text;

    public ServiceDescription() {
    }

    public Language getLanguage() {
        return language;
    }

    public ServiceDescription setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServiceDescription setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public ServiceDescription setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceDescription{" +
                "language=" + (language == null ? "null" : language) +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
