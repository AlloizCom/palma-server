package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.config.Constants;
import com.alloiz.palma.server.dto.ScheduleByPages;
import com.alloiz.palma.server.dto.ScheduleDto;
import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.repository.ScheduleRepository;
import com.alloiz.palma.server.repository.utils.ChangeRoomForSale;
import com.alloiz.palma.server.repository.utils.RoomTypeWithNumber;
import com.alloiz.palma.server.service.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.List;
import java.util.Locale;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static com.alloiz.palma.server.service.utils.Validation.*;
import static java.util.stream.Collectors.toList;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger LOGGER = Logger.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public Schedule findOneAvailable(Long id) {
        checkId(id);
        return scheduleRepository.findByAvailableAndId(true,id);
    }

    @Override
    public List<Schedule> findAllAvailable() {
        return scheduleRepository.findAllByAvailable(true);
    }

    @Override
    public List<Schedule> findAllArchived() {
        return scheduleRepository.findAllByAvailable(false);
    }

    @Override
    public Schedule findOne(Long id) {
        checkId(id);
        return scheduleRepository.findOne(id);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findAllByDateAndPlaces(Timestamp today, Integer places){
        return scheduleRepository
                .findAllByTodayAndForSaleAndAvailable(today,places,true);
    }

    @Override
    public List<Schedule> findByParam(Timestamp today, Integer places){
        return scheduleRepository.findByTodayAndForSale(today,places);
    }

    @Override
    public Schedule save(Schedule schedule) {
        checkSave(schedule);
        return scheduleRepository.save(schedule.setAvailable(true)
                .setToday(Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    public Schedule saveDefault(Schedule schedule) {
        return scheduleRepository.save(schedule.setAvailable(true));
    }

    @Override
    public Schedule update(Schedule schedule) {
        checkObjectExistsById(schedule.getId(),scheduleRepository);
        Integer free = schedule.getForSale() - schedule.getActive();
        return scheduleRepository.save(findOne(schedule.getId())
                    .setForSale(schedule.getForSale())
                    .setActive(schedule.getActive())
                    .setFree(free)
                    .setAvailable(schedule.getAvailable()));
    }

    @Override
    public Schedule updateAfterBooking(Schedule schedule) {
        checkObjectExistsById(schedule.getId(),scheduleRepository);
        return scheduleRepository.save(findOne(schedule.getId())
                .setForSale(schedule.getForSale())
                .setActive(schedule.getActive())
                .setFree(schedule.getFree())
                .setAvailable(schedule.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            scheduleRepository.delete(checkObjectExistsById(id, scheduleRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Schedule> findAll(Pageable pageable) {
        List<Schedule> schedules = scheduleRepository.findAll(pageable).getContent();
        return schedules;
    }

    @Override
    public ScheduleByPages findAllByAvailable(Pageable pageable) {
        LOGGER.info(">>> Page number:" + pageable.getPageNumber());
        LOGGER.info(">>> Page size:" + pageable.getPageSize());
        List<ScheduleDto> scheduleList = scheduleRepository
                .findAllByAvailable(true, pageable)
                .getContent()
                .stream()
                .map(schedule -> map(schedule, ScheduleDto.class))
                .collect(toList());
//        LOGGER.info("-------------Schedule Page---------------");
//        scheduleList.stream().forEach(n -> LOGGER.info(n.getId()));
//        LOGGER.info("-----------------------------------------");
//        scheduleList.stream().forEach(n -> LOGGER.info(n.getId()));
        return new ScheduleByPages()
                .setShedules(scheduleList)
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize())
                .setNumberOfPages((scheduleRepository
                .countAllByAvailable(true) / pageable.getPageSize()) + 1);
    }

    @Override
    public List<Schedule> findAllByAvailableAndTodayAfterAndRoomType(Timestamp today, RoomType roomType) {
        return scheduleRepository.findAllByAvailableAndTodayAfterAndTodayBeforeAndRoomType(true, today, Timestamp.valueOf(today.toLocalDateTime().plusMonths(1L)), roomType);
    }

    @Override
    public List<Schedule> findByParamForBook(Timestamp dateIn, Timestamp dateOut, RoomType roomType){
        return scheduleRepository.findRoomBetweenDateWithRoomType(dateIn,dateOut,roomType);
    }

    @Override
    public Boolean runBySchedule(){

        scheduleRepository.findByTodayDate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)
                .withHour(0).withMinute(0).withSecond(0).withNano(0)))
                .stream().forEach(room -> update(room.setAvailable(false)));

        Schedule schedule;
        try {
            schedule = findAll().get(findAll().size() - 1);
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            schedule=new Schedule().setToday(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
        }
        LOGGER.info(schedule);


        LOGGER.info("Schedule full check "+( dataComparer(Timestamp.valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
                .plusDays(Constants.MAX_DAYS_TO_BOOK)),schedule.getToday())));

        LOGGER.info("PlusDay Local "+Timestamp.valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
                .plusDays(Constants.MAX_DAYS_TO_BOOK)));

        if( dataComparer(Timestamp.valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
                        .plusDays(Constants.MAX_DAYS_TO_BOOK)),schedule.getToday())) {
            return false;
        }

        for(int i=1;i<=dateMinuser(
                    Timestamp.valueOf(LocalDateTime.now().plusDays(Constants.MAX_DAYS_TO_BOOK))
                    ,schedule.getToday()); i++ ){

            saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.STANDARD)
                .setToday(Timestamp
                        .valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).plusDays(i))));
            saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.STANDARD_IMPROVED)
                    .setToday(Timestamp
                            .valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).plusDays(i))));
            saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.SUPERIOR)
                    .setToday(Timestamp
                            .valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).plusDays(i))));
            saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.SUPERIOR_IMPROVED)
                    .setToday(Timestamp
                            .valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).plusDays(i))));
            saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.DELUXE)
                    .setToday(Timestamp
                            .valueOf(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).plusDays(i))));
        }

        return true;
    }

    public Boolean refreshSchedule(){
        LOGGER.info("-------------Schedule Refresh---------------");


        return true;
    }

    @Override
    public Boolean changeRoomForSale(ChangeRoomForSale changeRoomForSale) {

        for(RoomTypeWithNumber type: changeRoomForSale.getRooms()){
            List<Schedule> scheduleList = scheduleRepository.findRoomBetweenDateWithRoomType(changeRoomForSale.getDateFrom(),
                    changeRoomForSale.getDateTo(),type.getRoomType());
            scheduleList.stream().forEach(schedule -> {
                for(String day: changeRoomForSale.getDaysOfWeek()){
                    if(day.equals(new SimpleDateFormat("EEEE",Locale.ENGLISH).format(schedule.getToday()))){
                        schedule.setForSale(type.getNumberOfRoom());
                        update(schedule);
                    }
                }
            });
        }

        return null;
    }

    @Override
    public List<Schedule> findRoomByDateinAndDateOut(Timestamp dateIn, Timestamp dateOut) {
        return scheduleRepository.findRoomByDateinAndDateOut(dateIn,dateOut);
    }

    private static Integer dateMinuser(Timestamp left, Timestamp right){ // returns difference by dates in DAYS

        LocalDateTime localLeft = left.toLocalDateTime();
        LocalDateTime localRight = right.toLocalDateTime();
        int resultDays=0;

        if (localLeft.getYear()>localRight.getYear())
            resultDays+=365*(localLeft.getYear() - localRight.getYear());

        if(localLeft.getMonthValue()>localRight.getMonthValue())
            resultDays += 30*(localLeft.getMonthValue()-localRight.getMonthValue());

        if(localLeft.getMonthValue()>localRight.getMonthValue() || localLeft.getYear()>localRight.getYear() )
            resultDays += localLeft.getDayOfMonth()-localRight.getDayOfMonth();

        return resultDays;
    }

    public static Boolean dataComparer(Timestamp leftDate, Timestamp rightDate){
        String [] leftSplit=leftDate.toString().split("-");
        String [] rightSplit=rightDate.toString().split("-");
        Integer leftYear=Integer.parseInt(leftSplit[0]);
        Integer rightYear=Integer.parseInt(rightSplit[0]);
        if(leftYear>rightYear) {
            return false;
        }
        Integer leftMonth=Integer.parseInt(leftSplit[1]);
        Integer rightMonth=Integer.parseInt(rightSplit[1]);
        if (leftMonth>rightMonth){
            return false;
        }
        Integer leftDay=Integer.parseInt(leftSplit[2].split(" ")[0]);
        Integer rightDay=Integer.parseInt(rightSplit[2].split(" ")[0]);
        if (!leftDay.equals(rightDay)) {
            return false;
        }
        return true;
    }


}
