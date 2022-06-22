package dao;

import model.SpecificDay;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.util.List;

public interface SpecificDayDao {

    public List<SpecificDay> getSpecificDays();

    public List<SpecificDay> getSpecificDaysByPersonId(int personId);

    public List<SpecificDay> getSpecificDaysByDate(LocalDate date);

    public SpecificDay getSpecificDay(int personId, LocalDate date);

    public SpecificDay addSpecificDay(SpecificDay specificDay);

    public void updateSpecificDay(SpecificDay specificDay);

    public void deleteSpecificDay(int personId, LocalDate date);

}
