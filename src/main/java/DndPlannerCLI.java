import dao.*;
import model.Person;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.ArrayList;
import java.util.List;

public class DndPlannerCLI {

    /*ToDo
    Write interface for CampaignDao
    Create POJO model for Campaign
    Refactor DayOfWeekDao
    Impement JdbcSpecificDayDao
    Implement JdbcCampaignDao
     */


    private final PersonDao personDao;
    private final DayOfWeekDao dayOfWeekDao;
    //private final SpecificDayDao specificDayDao;
    //private final CampaignDao campaignDao;

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Testing");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        DndPlannerCLI application = new DndPlannerCLI(dataSource);
        application.run();
    }

    public DndPlannerCLI(BasicDataSource dataSource){
        personDao = new JdbcPersonDao(dataSource);
        dayOfWeekDao = new JdbcDayOfWeekDao(dataSource);
        //specificDayDao = new JdbcSpecificDayDao(dataSource);
        //campaignDao = new JdbcCampaignDao(dataSource);
    }

    public void run(){



    }

}
