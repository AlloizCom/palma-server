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
    private String headerText;

    @Column(columnDefinition = "LONGTEXT")
    private String mainText;

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

    public String getHeaderText() {
        return headerText;
    }

    public ServiceDescription setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    public String getMainText() {
        return mainText;
    }

    public ServiceDescription setMainText(String mainText) {
        this.mainText = mainText;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceDescription{" +
                "language=" + (language == null ? "null" : language) +
                ", title='" + title + '\'' +
                ", headerText='" + headerText + '\'' +
                ", mainText='" + mainText + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
