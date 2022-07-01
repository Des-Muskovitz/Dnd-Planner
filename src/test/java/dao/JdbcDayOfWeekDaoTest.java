package dao;

import com.sam.dao.JdbcDayOfWeekDao;
import org.junit.Before;

public class JdbcDayOfWeekDaoTest extends BaseDaoTest {

    private JdbcDayOfWeekDao sut;

    @Before
    public void setUp(){
        sut = new JdbcDayOfWeekDao(dataSource);
    }

    public void testGetAllDaysOfWeek() {
    }

    public void testGetDayOfWeekByPersonId() {
    }

    public void testGetDayOfWeekByDayCode() {
    }

    public void testGetDayOfWeekByCampaign() {
    }

    public void testGetDayOfWeek() {
    }

    public void testAddDayOfWeek() {
    }

    public void testUpdateDayOfWeek() {
    }

    public void testDeleteDayOfWeek() {
    }
}