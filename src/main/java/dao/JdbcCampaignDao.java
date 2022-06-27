package dao;

import model.Campaign;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCampaignDao implements CampaignDao{

    private JdbcTemplate jdbcTemplate;
    private static final String START_OF_SELECT_SQL = "SELECT c.campaign_id, c.name, c.description FROM campaign c";
    private static final String GET_CAMPAIGNS_SQL = START_OF_SELECT_SQL + ";";
    private static final String GET_CAMPAIGNS_BY_PERSON_SQL = START_OF_SELECT_SQL + " JOIN campaign_person cp ON cp.campaign_id = c.campaign_id WHERE cp.person_id = ?;";
    private static final String GET_CAMPAIGN_BY_ID_SQL = START_OF_SELECT_SQL + " WHERE c.campaign_id = ?;";
    private static final String ADD_CAMPAIGN_SQL = "INSERT INTO campaign (name, description) VALUES (?,?) RETURING campaign_id;";
    private static final String ADD_PLAYER_TO_CAMPAING_SQL = "INSERT INTO campaign_person (campaign_id, person_id) VALUES (?,?);";
    private static final String UPDATE_CAMPAIGN_SQL = "UPDATE campaign SET name = ?, description = ? WHERE campaign_id = ?;";
    private static final String DELETE_CAMPAIGN_SQL = "DELETE FROM campaign WHERE campaign_id = ?;";

    public JdbcCampaignDao(DataSource dataSource){jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<Campaign> getCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_CAMPAIGNS_SQL);
        while(sqlRowSet.next()){
            campaigns.add(mapDataToCampaign(sqlRowSet));
        }
        return campaigns;
    }

    @Override
    public List<Campaign> getCampaignsByPerson(int personId) {
        List<Campaign> campaigns = new ArrayList<>();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_CAMPAIGNS_BY_PERSON_SQL, personId);
        while(sqlRowSet.next()){
            campaigns.add(mapDataToCampaign(sqlRowSet));
        }
        return campaigns;
    }

    @Override
    public Campaign getCampaignById(int campaignId) {
        Campaign campaign = null;
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(GET_CAMPAIGN_BY_ID_SQL, campaignId);
        if(sqlRowSet.next()){
            campaign = mapDataToCampaign(sqlRowSet);
        }
        return campaign;
    }

    @Override
    public Campaign addCampaign(Campaign newCampaign) {
        Integer campaignId = jdbcTemplate.queryForObject(ADD_CAMPAIGN_SQL, Integer.class, newCampaign.getName(), newCampaign.getDescription());
        return getCampaignById(campaignId);
    }

    @Override
    public void addPlayerToCampaign(int campaignId, int personId) {
        jdbcTemplate.update(ADD_PLAYER_TO_CAMPAING_SQL, campaignId, personId);
    }

    @Override
    public void updateCampaign(Campaign updatedCampaign) {
        jdbcTemplate.update(UPDATE_CAMPAIGN_SQL, updatedCampaign.getName(), updatedCampaign.getDescription());
    }

    @Override
    public void deleteCampaign(int campaignId) {
        jdbcTemplate.update(DELETE_CAMPAIGN_SQL, campaignId);
    }

    private Campaign mapDataToCampaign(SqlRowSet sqlRowSet){
        Campaign campaign = new Campaign();
        campaign.setCampaignId(sqlRowSet.getInt("campaign_id"));
        campaign.setName(sqlRowSet.getString("name"));
        campaign.setDescription(sqlRowSet.getString("description"));
        return campaign;
    }
}
