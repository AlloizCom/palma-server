package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Tariff;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.enums.TariffType;
import com.alloiz.palma.server.repository.TariffRepository;
import com.alloiz.palma.server.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;


/**
 * {@inheritDoc}
 */
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
    public List<Tariff> findByRoomType(RoomType roomType) {
        return tariffRepository.findAllByAvailableAndRoomType(true, roomType);
    }

    @Override
    public List<Tariff> findByTariffType(TariffType tariffType) {
        return tariffRepository.findAllByAvailableAndTariffType(true, tariffType);
    }

    @Override
    public List<Tariff> findByTariffTypeAndRoomType(TariffType tariffType, RoomType roomType) {
        return tariffRepository.findAllByAvailableAndTariffTypeAndRoomType(true, tariffType, roomType);
    }

    @Override
    public Tariff findByRoomTypeAndDateBetween(RoomType roomType, Timestamp date) {
        List<Tariff> ret = tariffRepository.findAllByAvailableAndRoomTypeAndDateFromBeforeAndDateToAfterAndTariffType(true, roomType, date, date, TariffType.SPECIAL);
        return ret.stream().findFirst().orElse(tariffRepository.findAllByAvailableAndTariffTypeAndRoomType(true, TariffType.REGULAR,roomType).stream().findFirst().orElse(new Tariff()
                .setPrice(1)
                .setTariffType(TariffType.REGULAR)
                .setRoomType(roomType)
        ));
    }

    @Override
    public Tariff findByRoomTypeAndDateNow(RoomType roomType) {
        return findByRoomTypeAndDateBetween(roomType, Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public List<Tariff> findAllDateNow() {
        return findAllDateBetween(Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public List<Tariff> findAllDateBetween(Timestamp date) {
        List<Tariff> ret = tariffRepository.findAllByAvailableAndDateFromBeforeAndDateToAfterAndTariffType(true,date,date, TariffType.SPECIAL);
        if(ret.size()<1){
            ret = tariffRepository.findAllByAvailableAndTariffType(true, TariffType.REGULAR);
        }
        return ret;
    }

    @Override
    public Tariff save(Tariff tariff) {
        checkSave(tariff);
        if (tariff.getRoomType() == null) {
            tariff.setRoomType(RoomType.NONE);
        }
        if (tariff.getTariffType() == null) {
            tariff.setTariffType(TariffType.NONE);
        }
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

    @Override
    public Boolean createDefaultTariffs(boolean create){
        if (tariffRepository.findAllByAvailableAndTariffType(true,TariffType.REGULAR)
                .size()<RoomType.values().length-2){
            for (RoomType roomType: RoomType.values()
                 ) {
                if(!roomType.equals(RoomType.NONE)){
                    tariffRepository.save(new Tariff()
                            .setPrice(999)
                            .setRoomType(roomType)
                            .setTariffType(TariffType.REGULAR)
                            .setAvailable(true)
                            .setDateFrom(Timestamp.valueOf(LocalDateTime.of(2018,1,1,0,0,0)))
                            .setDateTo(Timestamp.valueOf(LocalDateTime.of(2018,12,31,0,0,0)))
                    );
                }
            }
            return true;
        }
        return false;
    }

}
