package dao;

import model.Campaign;
import model.Person;

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
     * @param newCampaign
     * @return
     */
    public Campaign addCampaign(Campaign newCampaign);

    public boolean addPlayerToCampaign(int CampaignId, Person personToAdd);

    public void updateCampaign(Campaign updatedCampaign);

    public void deleteCampaign(int CampaignId);
}
