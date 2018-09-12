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

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.alloiz.palma.server.repository")
public class ServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(ServerApplication.class, args);
        generateSchedule(context);
    }

    private static void generateSchedule(ConfigurableApplicationContext context){
        ScheduleService scheduleRepository = context.getBean(ScheduleService.class);

        if(scheduleRepository.findAll().size() < 100){
            for (int i = 0;i < 20;i++) {
                scheduleRepository.save(new Schedule().setForSale(6).setActive(0).setFree(6).setRoomType(RoomType.DELUXE));
                scheduleRepository.save(new Schedule().setForSale(6).setActive(0).setFree(6).setRoomType(RoomType.STANDARD));
                scheduleRepository.save(new Schedule().setForSale(6).setActive(0).setFree(6).setRoomType(RoomType.STANDARD_IMPROVED));
                scheduleRepository.save(new Schedule().setForSale(6).setActive(0).setFree(6).setRoomType(RoomType.SUPERIOR));
                scheduleRepository.save(new Schedule().setForSale(6).setActive(0).setFree(6).setRoomType(RoomType.SUPERIOR_IMPROVED));
            }
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServerApplication.class);
    }
}
