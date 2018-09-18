package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class ProposalDescription {

    @Enumerated(EnumType.STRING)
    private Language language;

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String headerText;

    @Column(columnDefinition = "LONGTEXT")
    private String mainText;

    public ProposalDescription() {
    }

    public Language getLanguage() {
        return language;
    }

    public ProposalDescription setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProposalDescription setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getHeaderText() {
        return headerText;
    }

    public ProposalDescription setHeaderText(String headerText) {
        this.headerText = headerText;
        return this;
    }

    public String getMainText() {
        return mainText;
    }

    public ProposalDescription setMainText(String mainText) {
        this.mainText = mainText;
        return this;
    }

    @Override
    public String toString() {
        return "ProposalDescription{" +
                "language=" + language +
                ", title='" + title + '\'' +
                ", headerText='" + headerText + '\'' +
                ", mainText='" + mainText + '\'' +
                '}';
    }
}
