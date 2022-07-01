package com.sam.dao;

import com.sam.model.DayOfWeek;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component("ActiveDayOfWeekDao")
public class JdbcDayOfWeekDao implements DayOfWeekDao{
    private final JdbcTemplate jdbcTemplate;
    private static final String START_OF_SELECT_STATEMENT_SQL = "SELECT person_id, day_code, is_free, start_time, end_time FROM days_of_week";
    private static final String GET_ALL_DAYS_OF_WEEK_SQL = START_OF_SELECT_STATEMENT_SQL+";";
    private static final String GET_DAY_OF_WEEK_BY_PERSON_ID_SQL = START_OF_SELECT_STATEMENT_SQL + " WHERE person_id = ?;";
    private static final String GET_DAY_OF_WEEK_BY_DAY_CODE_SQL = START_OF_SELECT_STATEMENT_SQL + " WHERE day_code = ?;";
    private static final String GET_DAY_OF_WEEK_BY_CAMPAIGN_ID_SQL = START_OF_SELECT_STATEMENT_SQL + " WHERE campaign_id = ?;";
    private static final String GET_DAY_OF_WEEK_SQL = START_OF_SELECT_STATEMENT_SQL + " WHERE person_id = ? AND day_code = ?";
    private static final String ADD_DAY_OF_WEEK_SQL = "INSERT INTO day_of_week (person_id, day_code, is_free, start_time, end_time) VALUES (?,?,?,?,?)";
    private static final String UPDATE_DAY_OF_WEEK_SQL = "UPDATE day_of_week SET person_id = ?, day_code = ?, is_free = ?, start_time = ? end_time = ? WHERE person_id = ? AND day_code = ?;";
    private static final String DELETE_DAY_OF_WEEK_SQL = "DELETE FROM day_of_week WHERE person_id = ? AND day_code = ?;";

    public JdbcDayOfWeekDao(DataSource dataSource){this.jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<DayOfWeek> getAllDaysOfWeek() {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_ALL_DAYS_OF_WEEK_SQL);
        while(sqlRowSet.next()){
            dayOfWeeks.add(mapDataToDayOfWeek(sqlRowSet));
        }
        return dayOfWeeks;
    }

    @Override
    public List<DayOfWeek> getDayOfWeekByPersonId(int personId) {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_DAY_OF_WEEK_BY_PERSON_ID_SQL, personId);
        while(sqlRowSet.next()){
            dayOfWeeks.add(mapDataToDayOfWeek(sqlRowSet));
        }
        return dayOfWeeks;
    }

    @Override
    public List<DayOfWeek> getDayOfWeekByDayCode(String dayCode) {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_DAY_OF_WEEK_BY_DAY_CODE_SQL, dayCode);
        while(sqlRowSet.next()){
            dayOfWeeks.add(mapDataToDayOfWeek(sqlRowSet));
        }
        return dayOfWeeks;
    }

    @Override
    public List<DayOfWeek> getDayOfWeekByCampaign(int campaignId) {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_DAY_OF_WEEK_BY_CAMPAIGN_ID_SQL, campaignId);
        while(sqlRowSet.next()){
            dayOfWeeks.add(mapDataToDayOfWeek(sqlRowSet));
        }
        return dayOfWeeks;
    }

    @Override
    public DayOfWeek getDayOfWeek(int personId, String dayCode) {
        DayOfWeek dayOfWeek = null;
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_DAY_OF_WEEK_SQL, personId, dayCode);
        if(sqlRowSet.next()){
            dayOfWeek = mapDataToDayOfWeek(sqlRowSet);
        }
        return dayOfWeek;
    }

    @Override
    public DayOfWeek addDayOfWeek(DayOfWeek dayOfWeek) {
        jdbcTemplate.update(ADD_DAY_OF_WEEK_SQL, dayOfWeek.getPersonId(), dayOfWeek.getDayCode(), dayOfWeek.isFree(), dayOfWeek.getStartTime(), dayOfWeek.getEndTime());
        return getDayOfWeek(dayOfWeek.getPersonId(), dayOfWeek.getDayCode());
    }

    @Override
    public void updateDayOfWeek(DayOfWeek dayOfWeek) {
        jdbcTemplate.update(UPDATE_DAY_OF_WEEK_SQL, dayOfWeek.getPersonId(), dayOfWeek.getDayCode(), dayOfWeek.isFree(), dayOfWeek.getStartTime(), dayOfWeek.getEndTime());
    }

    @Override
    public void deleteDayOfWeek(int personId, String dayCode) {
        jdbcTemplate.update(DELETE_DAY_OF_WEEK_SQL, personId, dayCode);
    }

    /**
     * Maps a database row to a DayOfWeek Object
     *
     * @param sqlRowSet The return from a database pull
     * @return A DayOfWeek Object
     */
    private DayOfWeek mapDataToDayOfWeek(SqlRowSet sqlRowSet){
        DayOfWeek dayOfWeek = new DayOfWeek();
        dayOfWeek.setPersonId(sqlRowSet.getInt("person_id"));
        dayOfWeek.setDayCode(sqlRowSet.getString("day_code"));
        dayOfWeek.setFree(sqlRowSet.getBoolean("is_free"));
        if(sqlRowSet.getTime("start_time") != null){
            dayOfWeek.setStartTime(sqlRowSet.getTime("start_time").toLocalTime());
        }
        if(sqlRowSet.getTime("end_time") != null){
            dayOfWeek.setEndTime(sqlRowSet.getTime("end_time").toLocalTime());
        }
        return dayOfWeek;
    }
}
