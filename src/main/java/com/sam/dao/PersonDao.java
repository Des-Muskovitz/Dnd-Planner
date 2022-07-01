package com.sam.dao;

import com.sam.model.Person;

import java.util.List;

public interface PersonDao {


    /**
     *Gets all people from a data source and returns them in a list
     *
     * @return all the people as Person objects in a list
     */
    public List<Person> getAllPeople();

    /**
     * Gets all people from a data source by Campaign and returns them in a list
     *
     * @param campaignId A valid campaign id, if invalid list will be empty
     * @return A list of Person Objects
     */
    public List<Person> getAllPeopleByCampaign(int campaignId);

    /**
     * Gets a specific person from a data source by their id and returns as a Person object
     *
     * @param personId The id of the person to find
     * @return Returns a Person Object, returns null if person is not found
     */
    public Person getPerson(int personId);

    /**
     * adds a person to the data source and returns a new Person object containing the new ID
     *
     * @param newPerson A person object with all the data filled in
     * @return A new person object with a generated Id
     */
    public Person addPerson(Person newPerson);

    /**
     * Updates a person that currently exists within a database
     * can only be called on a person that exists
     *
     * @param newPerson Contains all the information that needs to be updated to database,
     *                  MUST CONTAIN a valid Id Code or update will not apply
     */
    public void updatePerson(int id, Person newPerson);

    /**
     * Removes a person that currently exists within a database
     * can only be called on a person that exists
     *
     * @param personId Must be a valid Id Code or delete will not apply
     */
    public void deletePerson(int personId);
}
