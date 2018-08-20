package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class ServiceShortDto <T extends ServiceShortDto>{

    protected Long id;
    protected Boolean available;
    protected String name;

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

    @Override
    public String toString() {
        return "ServiceShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", name='" + name + '\'' +
                '}';
    }
}
