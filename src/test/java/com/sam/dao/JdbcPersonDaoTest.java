package com.sam.dao;

import com.sam.model.Person;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcPersonDaoTest extends BaseDaoTest {

    private static final Person PERSON_1 = new Person(1,"Sam Muskovitz");
    private static final Person PERSON_2 = new Person(2,"John Greaves");
    private static final Person PERSON_3 = new Person(3,"Jeanna Shellenburger");
    private static final Person PERSON_4 = new Person(4, "Brandon Leong");
    private static final Person PERSON_5 = new Person(5,"Matt VanTrease");
    private static final Person PERSON_6 = new Person(6,"Dana Coyle");
    private static final Person PERSON_7 = new Person(7,"Brandon Butler");
    private static final Person PERSON_8 = new Person(8,"Darah Muhamad");
    private static final Person PERSON_9 = new Person(9,"Neha Kamireddi");

    private static final List<Person> EXPECTED_PERSONS = new ArrayList<>(Arrays.asList(PERSON_1, PERSON_2, PERSON_3, PERSON_4, PERSON_5, PERSON_6, PERSON_7, PERSON_8, PERSON_9));

    private JdbcPersonDao sut;

    @Before
    public void setup(){
        sut = new JdbcPersonDao(dataSource);
    }

    @Test
    public void testGetAllPeople() {
        assertPersonMatchList(EXPECTED_PERSONS, sut.getAllPeople());
    }

    @Test
    public void testGetAllPeopleByCampaign() {
        assertPersonMatchList(new ArrayList<>(Arrays.asList(PERSON_1, PERSON_2, PERSON_3, PERSON_4, PERSON_5, PERSON_6)), sut.getAllPeopleByCampaign(1));
        assertPersonMatchList(new ArrayList<>(Arrays.asList(PERSON_1, PERSON_3, PERSON_4, PERSON_7, PERSON_9)), sut.getAllPeopleByCampaign(2));
        assertPersonMatchList(new ArrayList<>(Arrays.asList(PERSON_2, PERSON_5, PERSON_6, PERSON_8)), sut.getAllPeopleByCampaign(3));
    }

    @Test
    public void testGetPerson() {
        assertPersonMatch(PERSON_1, sut.getPerson(1));
        assertPersonMatch(PERSON_3, sut.getPerson(3));
    }

    @Test
    public void testAddPerson() {
        Person expected = sut.addPerson(new Person(-1, "bobi"));
        assertPersonMatch(expected, sut.getPerson(10));
    }

    @Test
    public void testUpdatePerson() {
        sut.updatePerson(1, new Person(-1, "John"));
        assertPersonMatch(new Person(1, "John"), sut.getPerson(1));
    }

    @Test
    public void testDeletePerson() {
        sut.deletePerson(1);
        assertNull(sut.getPerson(1));
    }

    private void assertPersonMatchList(List<Person> expectedList, List<Person> actualList){
        for(int i = 0; i < actualList.size(); i++){
            assertPersonMatch(expectedList.get(i), actualList.get(i));
        }
    }

    private void assertPersonMatch(Person expected, Person actual){
        assertEquals("PersonId does not match", expected.getPersonId(), actual.getPersonId());
        assertEquals("Name does not match", expected.getName(), actual.getName());
    }
}