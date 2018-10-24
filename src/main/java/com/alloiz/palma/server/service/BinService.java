package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Bin;
import com.alloiz.palma.server.model.enums.Language;

import java.util.List;

public interface BinService {

    Bin findOneAvailable(Long id);

    List<Bin> findAllAvailable();

    Bin findOne(Long id);

    List<Bin> findAll();

    Bin save(Bin bin, Language language);

    Bin update(Bin bin);

    Bin findByUuid(String uuid);

    Boolean delete(Long id);

}
