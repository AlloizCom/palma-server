package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Tariff;
import com.alloiz.palma.server.repository.TariffRepository;
import com.alloiz.palma.server.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class TariffServiceImpl implements TariffService {

    @Autowired
    private TariffRepository tariffRepository;

    @Override
    public Tariff findOneAvailable(Long id) {
        checkId(id);
        return tariffRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Tariff> findAllAvailable() {
        return tariffRepository.findAllByAvailable(true);
    }

    @Override
    public Tariff findOne(Long id) {
        checkId(id);
        return tariffRepository.findOne(id);
    }

    @Override
    public List<Tariff> findAll() {
        return tariffRepository.findAll();
    }

    @Override
    public Tariff save(Tariff tariff) {
        checkSave(tariff);
        tariff.setAvailable(true);
        return tariffRepository.save(tariff);
    }

    @Override
    public Tariff update(Tariff tariff) {
        checkObjectExistsById(tariff.getId(), tariffRepository);
        return tariffRepository.save(findOne(tariff.getId())
                .setDateFrom(tariff.getDateFrom())
                .setDateTo(tariff.getDateTo())
                .setPrice(tariff.getPrice())
                .setRoomType(tariff.getRoomType())
                .setTariffType(tariff.getTariffType())
                .setAvailable(tariff.getAvailable())
        );
    }

    @Override
    public Boolean delete(Long id) {
        try {
            tariffRepository.delete(checkObjectExistsById(id, tariffRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
