package com.sam.dao;

import com.sam.model.SpecificDay;

import java.time.LocalDate;
import java.util.List;

public interface SpecificDayDao {

    /**
     * Returns a list of all Specific Days within a datasource
     *
     * @return A list containing SpecificDay Objects
     */
    public List<SpecificDay> getSpecificDays();

    /**
     * Returns a list of Specific Days By person
     * Will return an empty list if an invalid personId is inputted
     *
     * @param personId A valid personId
     * @return A list containing SpecificDay Objects
     */
    public List<SpecificDay> getSpecificDaysByPersonId(int personId);

    /**
     * Returns a list of Specific Days by A spcific date
     * Will return an empty list if the date does not exist within the datasource
     *
     * @param date A date
     * @return A list containing SpecificDay Objects
     */
    public List<SpecificDay> getSpecificDaysByDate(LocalDate date);

    /**
     * Returns a specific Day based on If a person has entered data into the datasource indicating a specific date
     *
     * @param personId A person Id, if person id does not exist, will return an empty object
     * @param date A speicifc date in time, if that date has not been entered, will return an empty object
     * @return A SpecificDay Object
     */
    public SpecificDay getSpecificDay(int personId, LocalDate date);

    /**
     * Adds a specific Day to the entity table
     *
     * @param specificDay A valid SpecificDay object
     */
    public void addSpecificDay(SpecificDay specificDay);

    /**
     * Replaces all the values of the specific day with the new values passed in by the parameter
     *
     * @param specificDay An updated SpecificDay object, Must have a valid personId and date or no changes will apply
     */
    public void updateSpecificDay(SpecificDay specificDay);

    /**
     * Removes a specific day entity from datasource
     *
     * @param personId A valid person id, or data will not be saved
     * @param date An inputted date that matches with the person id, or data will not be saved
     */
    public void deleteSpecificDay(int personId, LocalDate date);

}
