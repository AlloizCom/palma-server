package com.alloiz.palma.server;

import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.repository.ScheduleRepository;
import com.alloiz.palma.server.service.ScheduleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.alloiz.palma.server.repository")
public class ServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(ServerApplication.class, args);
        generateSchedule(context);
    }

    private static void generateSchedule(ConfigurableApplicationContext context){
        ScheduleService scheduleService = context.getBean(ScheduleService.class);
        ScheduleRepository scheduleRepository = context.getBean(ScheduleRepository.class);

//        scheduleRepository.findByTodayDate(Timestamp.valueOf(LocalDateTime.now().minusDays(1)
//                .withHour(0).withMinute(0).withSecond(0).withNano(0)))
//                .stream().forEach(schedule -> scheduleService.update(schedule.setAvailable(false)));

//        if(scheduleRepository.findAll().size() < 100) {
//            int day = 0;
//            for (int i = 0; day < 30; i++, day++) {
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.DELUXE)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.STANDARD)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.STANDARD_IMPROVED)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.SUPERIOR)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.SUPERIOR_IMPROVED)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
////
//            }
//        }

//        if(scheduleRepository.findAll().size() < 100) {
//            int day = 0;
//            for (int i = 0; day < 30; i++, day++) {
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.DELUXE)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.STANDARD)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.STANDARD_IMPROVED)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.SUPERIOR)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.SUPERIOR_IMPROVED)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day).withHour(0).withMinute(0).withSecond(0).withNano(0))));
////
//            }
//        }


//        if(scheduleRepository.findAll().size() < 100){
//            int day = 0;
//            for (int i = 0;day < 30;i++, day++) {
//                scheduleService.saveDefault(new Schedule().setForSale(6).setActive(0).setFree(6).setRoomType(RoomType.DELUXE)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day))));
//                scheduleService.saveDefault(new Schedule().setForSale(2).setActive(0).setFree(2).setRoomType(RoomType.STANDARD)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day))));
//                scheduleService.saveDefault(new Schedule().setForSale(5).setActive(0).setFree(5).setRoomType(RoomType.STANDARD_IMPROVED)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day))));
//                scheduleService.saveDefault(new Schedule().setForSale(3).setActive(0).setFree(3).setRoomType(RoomType.SUPERIOR)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day))));
//                scheduleService.saveDefault(new Schedule().setForSale(1).setActive(0).setFree(1).setRoomType(RoomType.SUPERIOR_IMPROVED)
//                        .setToday(Timestamp.valueOf(LocalDateTime.now().plusDays(day))));
//            }
//        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServerApplication.class);
    }
}
