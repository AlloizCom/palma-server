package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.NewsDescription;

import java.util.List;

@Dto
public class NewsFullDto extends NewsShortDto<NewsFullDto>{

    protected List<NewsDescription> newsDescriptions;

    public NewsFullDto(List<NewsDescription> newsDescriptions) {
        this.newsDescriptions = newsDescriptions;
    }

    public List<NewsDescription> getNewsDescriptions() {
        return newsDescriptions;
    }

    public NewsFullDto setNewsDescriptions(List<NewsDescription> newsDescriptions) {
        this.newsDescriptions = newsDescriptions;
        return this;
    }

    @Override
    public String toString() {
        return "NewsFullDto{" +
                "newsDescriptions=" + newsDescriptions +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
