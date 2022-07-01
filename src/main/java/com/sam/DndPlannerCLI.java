package com.sam;

import com.sam.dao.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DndPlannerCLI {

    private final PersonDao personDao;
    private final DayOfWeekDao dayOfWeekDao;
    private final SpecificDayDao specificDayDao;
    private final CampaignDao campaignDao;

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Testing");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        DndPlannerCLI application = new DndPlannerCLI(dataSource);
        SpringApplication.run(DndPlannerCLI.class, args);
    }

    public DndPlannerCLI(BasicDataSource dataSource){
        personDao = new JdbcPersonDao(dataSource);
        dayOfWeekDao = new JdbcDayOfWeekDao(dataSource);
        specificDayDao = new JdbcSpecificDayDao(dataSource);
        campaignDao = new JdbcCampaignDao(dataSource);
    }

}
