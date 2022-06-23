package dao;

import model.DayOfWeek;

import java.util.List;

public interface DayOfWeekDao {

    /**
     * Returns all the days of the week from datasource in a list
     *
     * @return Returns a list
     */
    public List<DayOfWeek> getAllDaysOfWeek();

    /**
     * Returns all the days of the week in a list inputed by a specific person
     *
     * @param personId The id of a user
     * @return A list of all the days of the week related to the personId, can be null if personId does not exist in datasource
     */
    public List<DayOfWeek> getDayOfWeekByPersonId(int personId);

    /**
     * Returns a specific day of the week inputed by all people
     *
     * @param dayCode the day code of a day of the week
     * @return A list of all users avalability on that speicifc day
     */
    public List<DayOfWeek> getDayOfWeekByDayCode(String dayCode);

    /**
     * Returns all Days of the week inputed within a specific campaign
     *
     * @param campaignId The id of a campaign
     * @return Returns a lit of all days of a week linked with a speciic campaign
     */
    public List<DayOfWeek> getDayOfWeekByCampaign(int campaignId);

    /**
     * Returns a specific day of the week inputed by a specific person
     *
     * @param personId ID code of a specific person
     * @param dayCode the day code of a day of the week
     * @return A single DayOfWeek, can be null if an invalid personId or dayCode is inputed
     */
    public DayOfWeek getDayOfWeek(int personId, String dayCode);

    /**
     * Adds a day of the week to datasource, as well as avalability of a person
     *
     * @param dayOfWeek A completed day of week object to add
     * @return The same day of week object that was added to datasource, with an id provided by the datasource
     */
    public DayOfWeek addDayOfWeek(DayOfWeek dayOfWeek);

    /**
     * Updates a day of the week within data source
     * Day of week must already exist within datasource
     *
     * @param dayOfWeek Must be an already existing data source, will overwrite all existing values within datasource with the updated values
     */
    public void updateDayOfWeek(DayOfWeek dayOfWeek);

    /**
     * Removes a day of the week from data source
     * Day of week must already exist within datasource
     *
     * @param personId Must be a valid person id or Method will not apply changes
     * @param dayCode Must be a valid day code or method will not apply changes
     */
    public void deleteDayOfWeek(int personId, String dayCode);
}
