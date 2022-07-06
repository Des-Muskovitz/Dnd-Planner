package com.sam.controller;

import com.sam.dao.SpecificDayDao;
import com.sam.model.SpecificDay;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/day/specific/")
public class SpecificDayController {

    private SpecificDayDao dao;

    public SpecificDayController(@Qualifier("ActiveSpecificDayDao")SpecificDayDao dao){
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SpecificDay> getSpecificDays(){
        return dao.getSpecificDays();
    }

    @RequestMapping(path="{id}/person", method = RequestMethod.GET)
    public List<SpecificDay> getSpecificDaysByPersonId(@PathVariable int id){
        return dao.getSpecificDaysByPersonId(id);
    }

    @RequestMapping(path="{year}-{month}-{day}", method = RequestMethod.GET)
    public List<SpecificDay> getSpecificDaysByDate(@PathVariable int year, @PathVariable int month, @PathVariable int day){
        return dao.getSpecificDaysByDate(LocalDate.of(year, month, day));
    }

    @RequestMapping(path="{id}/person/{year}-{month}-{day}", method = RequestMethod.GET)
    public SpecificDay getSpecificDay(@PathVariable int id, @PathVariable int year, @PathVariable int month, @PathVariable int day){
        return dao.getSpecificDay(id, LocalDate.of(year, month, day));
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addSpecificDay(@RequestBody @Valid SpecificDay specificDay){
        dao.addSpecificDay(specificDay);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public void updateSpecificDay(@RequestBody @Valid SpecificDay specificDay){
        dao.updateSpecificDay(specificDay);
    }

    @RequestMapping(path = "{id}/person/{year}-{month}-{day}", method = RequestMethod.DELETE)
    public void deleteSpeciicDay(@PathVariable int id, @PathVariable int year, @PathVariable int month, @PathVariable int day){
        dao.deleteSpecificDay(id, LocalDate.of(year, month, day));
    }
}
