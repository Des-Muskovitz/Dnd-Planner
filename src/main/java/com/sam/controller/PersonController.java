package com.sam.controller;

import com.sam.dao.PersonDao;
import com.sam.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person/")
public class PersonController {

    private PersonDao dao;

    public PersonController(@Qualifier("ActivePersonDao")PersonDao dao){
        this.dao = dao;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Person> getPersons(){
        return dao.getAllPeople();
    }

    @RequestMapping(path="{id}/campaign", method = RequestMethod.GET)
    public List<Person> getAllPeopleByCampaign(@PathVariable int id){
        return dao.getAllPeopleByCampaign(id);
    }

    @RequestMapping(path="{id}", method=RequestMethod.GET)
    public Person getPerson(@PathVariable int id){
        return dao.getPerson(id);
    }

    @RequestMapping(method=RequestMethod.POST)
    public Person addPerson(@Valid @RequestBody Person person){
        return dao.addPerson(person);
    }

    @RequestMapping(path="{id}", method=RequestMethod.PUT)
    public void updatePerson(@PathVariable int id, @Valid @RequestBody Person person){
        dao.updatePerson(id, person);
    }

    @RequestMapping(path="{id}", method=RequestMethod.DELETE)
    public void deletePerson(@PathVariable int id){
        dao.deletePerson(id);
    }
}
