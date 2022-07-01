package com.sam.controller;

import com.sam.dao.CampaignDao;
import com.sam.model.Campaign;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/campaign/")
public class CampaignController {

    private CampaignDao dao;


    public CampaignController(@Qualifier("ActiveCampaignDao") CampaignDao dao){
        this.dao = dao;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Campaign> getCampaigns(){
        return dao.getCampaigns();
    }

    @RequestMapping(path = "{id}/person", method = RequestMethod.GET)
    public List<Campaign> getCampaignsByPersonId(@PathVariable int id){
        return dao.getCampaignsByPerson(id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Campaign getCampaignById(@PathVariable int id){
        return dao.getCampaignById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Campaign addCampaign(@RequestBody @Valid Campaign campaign){
        return dao.addCampaign(campaign);
    }

    @RequestMapping(path="{campaignId}/person/{personId}", method = RequestMethod.POST)
    public void addPlayerToCampaign(@PathVariable int campaignId, @PathVariable int personId){
        dao.addPlayerToCampaign(campaignId, personId);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateCampaign(@PathVariable int id, @RequestBody @Valid Campaign campaign){
        dao.updateCampaign(id, campaign);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteCampaign(@PathVariable int id){
        dao.deleteCampaign(id);
    }
}
