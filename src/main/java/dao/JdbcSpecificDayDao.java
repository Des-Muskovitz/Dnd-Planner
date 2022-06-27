package dao;

import model.SpecificDay;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcSpecificDayDao implements SpecificDayDao{
    private final JdbcTemplate jdbcTemplate;
    private static final String START_OF_SELECT_STATEMENT_SQL = "SELECT person_id, specific_date, is_free FROM specific_day";
    private static final String GET_SPECIFIC_DAYS_SQL = START_OF_SELECT_STATEMENT_SQL + ";";
    private static final String GET_SPECIFIC_DAYS_BY_PERSON_ID_SQL = START_OF_SELECT_STATEMENT_SQL + " WHERE person_id = ?;";
    private static final String GET_SPECIFIC_DAYS_BY_DATE_SQL = START_OF_SELECT_STATEMENT_SQL + " WHERE specific_date = ?;";
    private static final String GET_SPECIFIC_DAY_SQL = START_OF_SELECT_STATEMENT_SQL + " WHERE person_id = ? AND specific_date = ?;";
    private static final String ADD_SPECIFIC_DAY_SQL = "INSERT INTO specific_day (person_id, specific_date, is_free) VALUES (?,?,?);";
    private static final String UPDATE_SPECIFIC_DAY_SQL = "UPDATE specific_day SET is_free = ? WHERE person_id = ? AND specific_date = ?;";
    private static final String DELETE_SPECIFIC_DAY_SQL = "DELETE FROM specific_day WHERE person_id = ? AND specific_date = ?;";

    public JdbcSpecificDayDao(DataSource dataSource){jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<SpecificDay> getSpecificDays() {
        List<SpecificDay> specificDays = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_SPECIFIC_DAYS_SQL);
        while(sqlRowSet.next()){
            specificDays.add(mapDataToSpecificDay(sqlRowSet));
        }
        return specificDays;
    }

    @Override
    public List<SpecificDay> getSpecificDaysByPersonId(int personId) {
        List<SpecificDay> specificDays = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_SPECIFIC_DAYS_BY_PERSON_ID_SQL, personId);
        while(sqlRowSet.next()){
            specificDays.add(mapDataToSpecificDay(sqlRowSet));
        }
        return specificDays;
    }

    @Override
    public List<SpecificDay> getSpecificDaysByDate(LocalDate date) {
        List<SpecificDay> specificDays = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_SPECIFIC_DAYS_BY_DATE_SQL, date);
        while(sqlRowSet.next()){
            specificDays.add(mapDataToSpecificDay(sqlRowSet));
        }
        return specificDays;
    }

    @Override
    public SpecificDay getSpecificDay(int personId, LocalDate date) {
        SpecificDay specificDay = null;
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_SPECIFIC_DAY_SQL, personId, date);
        if(sqlRowSet.next()){
            specificDay = mapDataToSpecificDay(sqlRowSet);
        }
        return specificDay;
    }

    @Override
    public void addSpecificDay(SpecificDay specificDay) {
        jdbcTemplate.update(ADD_SPECIFIC_DAY_SQL, specificDay.getPersonId(), specificDay.getDate(), specificDay.isFree());
    }

    @Override
    public void updateSpecificDay(SpecificDay specificDay) {
        jdbcTemplate.update(UPDATE_SPECIFIC_DAY_SQL, specificDay.isFree(), specificDay.getPersonId(), specificDay.getDate());
    }

    @Override
    public void deleteSpecificDay(int personId, LocalDate date) {
        jdbcTemplate.update(DELETE_SPECIFIC_DAY_SQL, personId, date);
    }

    private static SpecificDay mapDataToSpecificDay(SqlRowSet sqlRowSet){
        SpecificDay specificDay = new SpecificDay();
        specificDay.setPersonId(sqlRowSet.getInt("person_id"));
        specificDay.setDate(sqlRowSet.getDate("specific_date").toLocalDate());
        specificDay.setFree(sqlRowSet.getBoolean("is_free"));
        return specificDay;
    }
}
