package com.sam.controller;

import com.sam.dao.PersonDao;
import com.sam.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private PersonDao dao;

    public PersonController(PersonDao dao){
        this.dao = dao;
    }

    @RequestMapping(path="/person", method= RequestMethod.GET)
    public List<Person> getPersons(){
        return dao.getAllPeople();
    }
}
