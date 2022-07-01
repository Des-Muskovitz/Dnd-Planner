package com.sam.dao;

import com.sam.model.Campaign;

import java.util.List;

public interface CampaignDao {

    /**
     * Returns a list of all campaigns within the campaign table
     *
     * @return A list of Object Campaign
     */
    public List<Campaign> getCampaigns();

    /**
     * Returns a list of Campaigns by personId
     *
     * @param personId The ID code of a person entity, must be a valid code
     * @return A list of Campaign Objects, will be empty if the personId doesn't exist in the database or connect ot any campaigns
     */
    public List<Campaign> getCampaignsByPerson(int personId);

    /**
     * Returns a campaign object by the campaign Id
     *
     * @param campaignId The ID code of a campaign entity, must be a valid code
     * @return A sigle Campaign Object, will return null if ID is not valid
     */
    public Campaign getCampaignById(int campaignId);

    /**
     * Adds a campaign to the entity table
     *
     * @param newCampaign A valid campaign object
     * @return returns the same campaign object that was passed through, but with an updated campaignId
     */
    public Campaign addCampaign(Campaign newCampaign);

    /**
     * Adds a player to a campaign
     *
     * @param CampaignId The id of the campaign that the player is being added too
     * @param personId The id of the person that is being added to the campaign
     * */
    public void addPlayerToCampaign(int campaignId, int personId);

    /**
     * Replaces all the values of the campaign with the new values passed in by the parameter
     *
     * @param updatedCampaign An updated campaign object, Must have a valid campaign id or no changes will apply
     */
    public void updateCampaign(int id, Campaign updatedCampaign);

    /**
     * Removes a Campaign entity from the datasource
     *
     * @param CampaignId Must be a valid CampaignId
     */
    public void deleteCampaign(int campaignId);
}
