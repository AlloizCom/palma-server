package com.alloiz.palma.server.dto.utils.builder;


import com.alloiz.palma.server.dto.utils.DtoMapper;
import com.alloiz.palma.server.dto.utils.impl.DtoMapperImpl;

public class Builder {

    private static DtoMapper dtoMapper = new DtoMapperImpl();
    private static Object orElse;

    private Builder() {

    }

    @SafeVarargs
    public static <T> T map(Object dtoObject, Class<T>... parsingClasses) {
        return parsingClasses[0].cast(dtoMapper.parseFromDTOtoObject(dtoObject, parsingClasses));
    }
}
