package com.sam.dao;

import com.sam.model.SpecificDay;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcSpecificDayDaoTest extends BaseDaoTest {

    private static final SpecificDay SPECIFIC_DAY_1 = new SpecificDay(1, LocalDate.of(2022, 7, 5), true);
    private static final SpecificDay SPECIFIC_DAY_2 = new SpecificDay(2, LocalDate.of(2022, 7, 5), true);
    private static final SpecificDay SPECIFIC_DAY_3 = new SpecificDay(3, LocalDate.of(2022, 7, 5), false);
    private static final SpecificDay SPECIFIC_DAY_4 = new SpecificDay(4, LocalDate.of(2022, 7, 5), false);

    private static final SpecificDay SPECIFIC_DAY_5 = new SpecificDay(1, LocalDate.of(2022, 7, 29), false);
    private static final SpecificDay SPECIFIC_DAY_6 = new SpecificDay(2, LocalDate.of(2022, 7, 29), true);
    private static final SpecificDay SPECIFIC_DAY_7 = new SpecificDay(3, LocalDate.of(2022, 7, 29), true);
    private static final SpecificDay SPECIFIC_DAY_8 = new SpecificDay(4, LocalDate.of(2022, 7, 29), true);

    private static final List<SpecificDay> EXPECTED_SPECIFIC_DAYS = new ArrayList<>(Arrays.asList(SPECIFIC_DAY_1, SPECIFIC_DAY_2, SPECIFIC_DAY_3, SPECIFIC_DAY_4, SPECIFIC_DAY_5, SPECIFIC_DAY_6, SPECIFIC_DAY_7, SPECIFIC_DAY_8));

    private JdbcSpecificDayDao sut;

    @Before
    public void setUp() {
        sut = new JdbcSpecificDayDao(dataSource);
    }

    @Test
    public void testGetSpecificDays() {
        assertSpecificDayMatchesList(EXPECTED_SPECIFIC_DAYS, sut.getSpecificDays());
    }

    @Test
    public void testGetSpecificDaysByPersonId() {
        assertSpecificDayMatchesList(new ArrayList<>(Arrays.asList(SPECIFIC_DAY_1, SPECIFIC_DAY_5)), sut.getSpecificDaysByPersonId(1));
        assertSpecificDayMatchesList(new ArrayList<>(Arrays.asList(SPECIFIC_DAY_2, SPECIFIC_DAY_6)), sut.getSpecificDaysByPersonId(2));
        assertSpecificDayMatchesList(new ArrayList<>(Arrays.asList(SPECIFIC_DAY_3, SPECIFIC_DAY_7)), sut.getSpecificDaysByPersonId(3));
        assertSpecificDayMatchesList(new ArrayList<>(Arrays.asList(SPECIFIC_DAY_4, SPECIFIC_DAY_8)), sut.getSpecificDaysByPersonId(4));
    }

    @Test
    public void testGetSpecificDaysByDate() {
        assertSpecificDayMatchesList(new ArrayList<>(Arrays.asList(SPECIFIC_DAY_1, SPECIFIC_DAY_2, SPECIFIC_DAY_3, SPECIFIC_DAY_4)), sut.getSpecificDaysByDate(LocalDate.of(2022, 7, 5)));
        assertSpecificDayMatchesList(new ArrayList<>(Arrays.asList(SPECIFIC_DAY_5, SPECIFIC_DAY_6, SPECIFIC_DAY_7, SPECIFIC_DAY_8)), sut.getSpecificDaysByDate(LocalDate.of(2022, 7, 29)));
    }

    @Test
    public void testGetSpecificDay() {
        assertSpecificDayMatches(SPECIFIC_DAY_1, sut.getSpecificDay(1, LocalDate.of(2022, 7, 5)));
        assertSpecificDayMatches(SPECIFIC_DAY_5, sut.getSpecificDay(1, LocalDate.of(2022, 7, 29)));
    }

    @Test
    public void testAddSpecificDay() {
        SpecificDay expected = new SpecificDay(9, LocalDate.of(2022, 7, 29), false);
        sut.addSpecificDay(expected);

        assertSpecificDayMatches(expected, sut.getSpecificDay(9, LocalDate.of(2022, 7, 29)));
    }

    public void testUpdateSpecificDay() {
        SpecificDay expected = new SpecificDay(2, LocalDate.of(2022, 7, 29), false);
        sut.updateSpecificDay(expected);

        assertSpecificDayMatches(expected, sut.getSpecificDay(2, LocalDate.of(2022, 7, 29)));
    }

    public void testDeleteSpecificDay() {
        sut.deleteSpecificDay(1, LocalDate.of(2022, 7, 5));
        assertNull(sut.getSpecificDay(1, LocalDate.of(2022, 7, 5)));
    }

    private void assertSpecificDayMatchesList(List<SpecificDay> expectedList, List<SpecificDay> actualList){
        for(int i = 0; i < actualList.size(); i++){
            assertSpecificDayMatches(expectedList.get(i), actualList.get(i));
        }
    }

    private void assertSpecificDayMatches(SpecificDay expected, SpecificDay actual){
        assertEquals("PersonId does not match", expected.getPersonId(), actual.getPersonId());
        assertEquals("SpecificDate does not match", expected.getDate(), actual.getDate());
        assertEquals("isFree does not match", expected.isFree(), actual.isFree());
    }
}