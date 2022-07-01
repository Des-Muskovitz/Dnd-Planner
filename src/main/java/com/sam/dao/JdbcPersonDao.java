package com.sam.dao;

import com.sam.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPersonDao implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    private static final String START_SELECT_STATEMENT_SQL = "SELECT p.person_id, p.name FROM person p";
    private static final String GET_ALL_PEOPLE_SQL = START_SELECT_STATEMENT_SQL + ";";
    private static final String GET_PERSON_SQL = START_SELECT_STATEMENT_SQL + " WHERE p.person_id = ?";
    private static final String GET_ALL_PEOPLE_BY_CAMPAIGN_SQL = START_SELECT_STATEMENT_SQL + " JOIN campaign_person cp ON cp.person_id = p.person_id WHERE cp.campaign_id = ?;";
    private static final String ADD_PERSON_SQL = "INSERT INTO person (name) VALUES (?) RETURNING person_id;";
    private static final String UPDATE_PERSON_SQL = "UPDATE person SET name = ? WHERE person_id = ?;";
    private static final String DELETE_PERSON_SQL = "DELETE FROM days_of_week WHERE person_id = ?; DELETE FROM specific_days WHERE person_id = ?; DELETE FROM person WHERE person_id = ?;";

    public JdbcPersonDao(DataSource dataSource){this.jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<Person> getAllPeople() {
        List<Person> persons = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_ALL_PEOPLE_SQL);
        while(sqlRowSet.next()){
            persons.add(mapDataToPerson(sqlRowSet));
        }
        return persons;
    }

    @Override
    public List<Person> getAllPeopleByCampaign(int campaignId) {
        List<Person> persons = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_ALL_PEOPLE_BY_CAMPAIGN_SQL, campaignId);
        while(sqlRowSet.next()){
            persons.add(mapDataToPerson(sqlRowSet));
        }
        return persons;
    }

    @Override
    public Person getPerson(int personId) {
        Person person = null;
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_PERSON_SQL, personId);
        if(sqlRowSet.next()){
            person = mapDataToPerson(sqlRowSet);
        }
        return person;
    }


    @Override
    public Person addPerson(Person newPerson) {
        Integer personId = jdbcTemplate.queryForObject(ADD_PERSON_SQL, Integer.class, newPerson.getName());
        return getPerson(personId);
    }

    @Override
    public void updatePerson(Person newPerson) {
        jdbcTemplate.update(UPDATE_PERSON_SQL,newPerson.getName());
    }

    @Override
    public void deletePerson(int personId) {
        jdbcTemplate.update(DELETE_PERSON_SQL, personId);
    }

    /**
     * Maps a database row to a Person Object
     *
     * @param sqlRowSet The return from a database pull
     * @return A person Object
     */
    private Person mapDataToPerson(SqlRowSet sqlRowSet) {
        Person person = new Person();
        person.setPersonId(sqlRowSet.getInt("person_id"));
        person.setName(sqlRowSet.getString("name"));
        return person;
    }
}
