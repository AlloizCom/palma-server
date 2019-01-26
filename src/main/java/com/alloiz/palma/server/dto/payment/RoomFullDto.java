package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.payment.Description;

import java.util.List;


@Dto
public class RoomFullDto extends RoomShortDto<RoomFullDto> {

    private List<Description> descriptions;

    public List<Description> getDescriptions()
    {
        return descriptions;
    }

    public RoomFullDto setDescriptions(List<Description> descriptions)
    {
        this.descriptions = descriptions;
        return this;
    }

    public RoomFullDto() {
    }
}
