import dao.*;
import model.Campaign;
import model.DayOfWeek;
import model.Person;
import model.SpecificDay;
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
    private final SpecificDayDao specificDayDao;
    private final CampaignDao campaignDao;

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
        specificDayDao = new JdbcSpecificDayDao(dataSource);
        campaignDao = new JdbcCampaignDao(dataSource);
    }

    public void run(){
        List<Person> listOfPeople = personDao.getAllPeople();
        List<DayOfWeek> daysOfWeeks = dayOfWeekDao.getAllDaysOfWeek();
        List<SpecificDay> specificDays = specificDayDao.getSpecificDays();
        List<Campaign> campaigns = campaignDao.getCampaigns();

        for(Person p : listOfPeople){
            System.out.println(p);
        }
        for(DayOfWeek dow : daysOfWeeks){
            System.out.println(dow);
        }
        for(SpecificDay sd : specificDays){
            System.out.println(sd);
        }
        for(Campaign c : campaigns){
            System.out.println(c);
        }

    }

}
