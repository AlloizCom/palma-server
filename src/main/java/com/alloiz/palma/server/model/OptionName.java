package com.alloiz.palma.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OptionName extends BaseEntity<OptionName>{
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Option option;

    public OptionName() {
    }

    public String getName() {
        return name;
    }

    public OptionName setName(String name) {
        this.name = name;
        return this;
    }

    public Option getOption() {
        return option;
    }

    public OptionName setOption(Option option) {
        this.option = option;
        return this;
    }

    @Override
    public String toString() {
        return "OptionName{" +
                "name='" + name + '\'' +
                ", option=" + (option == null ? "null" : option) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
