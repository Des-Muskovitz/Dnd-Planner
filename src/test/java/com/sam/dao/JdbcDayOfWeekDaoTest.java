package com.sam.dao;

import com.sam.model.DayOfWeek;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JdbcDayOfWeekDaoTest extends BaseDaoTest {

    private static final DayOfWeek DAYOFWEEK_1 = new DayOfWeek(1,1, "MON", false, LocalTime.now(), LocalTime.now());
    private static final DayOfWeek DAYOFWEEK_2 = new DayOfWeek(1,1, "TUE", true, LocalTime.now(), LocalTime.now());
    private static final DayOfWeek DAYOFWEEK_3 = new DayOfWeek(2,1, "MON", true, LocalTime.now(), LocalTime.now());
    private static final DayOfWeek DAYOFWEEK_4 = new DayOfWeek(2,1, "TUE", false, LocalTime.now(), LocalTime.now());

    private JdbcDayOfWeekDao sut;
    private DayOfWeek expected = new DayOfWeek(1,2,"FRI", false, LocalTime.now(), LocalTime.now());

    private static final List<DayOfWeek> EXPECTED_DAYOFWEEKS = new ArrayList<>(Arrays.asList(DAYOFWEEK_1, DAYOFWEEK_2, DAYOFWEEK_3, DAYOFWEEK_4));

    @Before
    public void setUp(){
        DAYOFWEEK_1.setStartTime(LocalTime.now().minusHours(5));
        DAYOFWEEK_1.setEndTime(LocalTime.now().plusHours(5));

        DAYOFWEEK_2.setStartTime(LocalTime.now().minusHours(2));
        DAYOFWEEK_2.setEndTime(LocalTime.now().plusHours(2));

        DAYOFWEEK_3.setStartTime(LocalTime.now().minusHours(1));
        DAYOFWEEK_3.setEndTime(LocalTime.now().plusHours(1));

        DAYOFWEEK_4.setStartTime(LocalTime.now().minusHours(5));
        DAYOFWEEK_4.setEndTime(LocalTime.now().plusHours(5));

        expected.setStartTime(LocalTime.now().minusMinutes(5));
        expected.setEndTime(LocalTime.now().plusMinutes(5));

        sut = new JdbcDayOfWeekDao(dataSource);
    }

    @Test
    public void testGetAllDaysOfWeek() {
        assertDayOfWeekMatchList(EXPECTED_DAYOFWEEKS, sut.getAllDaysOfWeek());
    }

    @Test
    public void testGetDayOfWeekByPersonId() {
        assertDayOfWeekMatchList(new ArrayList<>(Arrays.asList(DAYOFWEEK_1, DAYOFWEEK_2)), sut.getDayOfWeekByPersonId(1));
        assertDayOfWeekMatchList(new ArrayList<>(Arrays.asList(DAYOFWEEK_3, DAYOFWEEK_4)), sut.getDayOfWeekByPersonId(2));
    }

    @Test
    public void testGetDayOfWeekByDayCode() {
        assertDayOfWeekMatchList(new ArrayList<>(Arrays.asList(DAYOFWEEK_1, DAYOFWEEK_3)), sut.getDayOfWeekByDayCode("MON"));
        assertDayOfWeekMatchList(new ArrayList<>(Arrays.asList(DAYOFWEEK_2, DAYOFWEEK_4)), sut.getDayOfWeekByDayCode("TUE"));
    }

    @Test
    public void testGetDayOfWeekByCampaign() {
        assertDayOfWeekMatchList(EXPECTED_DAYOFWEEKS, sut.getDayOfWeekByCampaign(1));
    }

    @Test
    public void testGetDayOfWeek() {
        assertDayOfWeekMatch(DAYOFWEEK_1, sut.getDayOfWeek(1, "MON"));
        assertDayOfWeekMatch(DAYOFWEEK_2, sut.getDayOfWeek(1, "TUE"));

        assertDayOfWeekMatch(DAYOFWEEK_3, sut.getDayOfWeek(2, "MON"));
        assertDayOfWeekMatch(DAYOFWEEK_4, sut.getDayOfWeek(2, "TUE"));
    }

    @Test
    public void testAddDayOfWeek() {
        DayOfWeek actual = sut.addDayOfWeek(expected);
        assertDayOfWeekMatch(expected, actual);
    }

    @Test
    public void testUpdateDayOfWeek() {
        DayOfWeek expected = new DayOfWeek(1,3, "MON", false, LocalTime.now().minusMinutes(4), LocalTime.now().plusMinutes(4));
        sut.updateDayOfWeek(expected);
        assertDayOfWeekMatch(expected, sut.getDayOfWeek(1,"MON"));
    }

    @Test
    public void testDeleteDayOfWeek() {
        sut.deleteDayOfWeek(1, "MON");
        assertNull(sut.getDayOfWeek(1, "MON"));
    }

    private void assertDayOfWeekMatchList(List<DayOfWeek> expectedList, List<DayOfWeek> actualList){
        for(int i = 0; i < actualList.size(); i++){
            assertDayOfWeekMatch(expectedList.get(i), actualList.get(i));
        }
    }

    private void assertDayOfWeekMatch(DayOfWeek expected, DayOfWeek actual){
        assertEquals("personID doesn't match", expected.getPersonId(), actual.getPersonId());
        assertEquals("campaignId doesn't match", expected.getCampaignId(), actual.getCampaignId());
        assertEquals("isFree doesn't match", expected.isFree(), actual.isFree());
        assertEquals("dayCode doesn't match", expected.getDayCode(), actual.getDayCode());
        assertEquals("startTime doesn't match",expected.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")), actual.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        assertEquals("endTime doesn't match", expected.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")), actual.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}