package com.sam.controller;

import com.sam.dao.DayOfWeekDao;
import com.sam.model.DayOfWeek;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dayofweek/")
public class DayOfWeekController {

    private DayOfWeekDao dao;

    public DayOfWeekController(@Qualifier("ActiveDayOfWeekDao") DayOfWeekDao dao){
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DayOfWeek> getAllDaysOfWeek(){
        return dao.getAllDaysOfWeek();
    }

    @RequestMapping(path = "{id}/person", method = RequestMethod.GET)
    public List<DayOfWeek> getDayOfWeekByPersonId(@PathVariable int id){
        return dao.getDayOfWeekByPersonId(id);
    }

    @RequestMapping(path = "{dayCode}", method = RequestMethod.GET)
    public List<DayOfWeek> getDayOfWeekByDayCode(@PathVariable String dayCode){
        return dao.getDayOfWeekByDayCode(dayCode);
    }

    @RequestMapping(path = "{id}/campaign", method = RequestMethod.GET)
    public List<DayOfWeek> getDayOfWeekByCampaignId(@PathVariable int id){
        return dao.getDayOfWeekByCampaign(id);
    }

    @RequestMapping(path = "{id}/person/{dayCode}", method = RequestMethod.GET)
    public DayOfWeek getDayOfWeek(@PathVariable int id, @PathVariable String dayCode){
        return dao.getDayOfWeek(id, dayCode);
    }

    @RequestMapping(method = RequestMethod.POST)
    public DayOfWeek addDayOfWeek(@Valid @RequestBody DayOfWeek dayOfWeek){
        return dao.addDayOfWeek(dayOfWeek);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateDayOfWeek(@Valid @RequestBody DayOfWeek dayOfWeek){
        dao.updateDayOfWeek(dayOfWeek);
    }

    @RequestMapping(path = "{id}/person/{dayCode}", method = RequestMethod.DELETE)
    public void deleteDayOfWeek(@PathVariable int id, @PathVariable String dayCode){
        dao.deleteDayOfWeek(id, dayCode);
    }
}
