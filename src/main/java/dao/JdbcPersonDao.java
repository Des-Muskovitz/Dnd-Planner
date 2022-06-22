package dao;

import model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersonDao implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_PEOPLE_SQL = "SELECT person_id, name, character_name FROM person;";
    private static final String GET_PERSON_SQL = "SELECT person_id, name, character_name FROM person WHERE person_id = ?";
    private static final String ADD_PERSON_SQL = "INSERT INTO person (name, character_name) VALUES (?,?) RETURNING person_id;";
    private static final String UPDATE_PERSON_SQL = "UPDATE person SET name = ?, character_name = ? WHERE person_id = ?;";
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
        Integer personId = jdbcTemplate.queryForObject(ADD_PERSON_SQL, Integer.class, newPerson.getName(), newPerson.getCharacterName());
        return getPerson(personId);
    }

    @Override
    public void updatePerson(Person newPerson) {
        jdbcTemplate.update(UPDATE_PERSON_SQL,newPerson.getName(),newPerson.getCharacterName());
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
        person.setCharacterName(sqlRowSet.getString("character_name"));
        return person;
    }
}
