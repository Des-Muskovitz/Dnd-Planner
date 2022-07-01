package dao;

import com.sam.dao.JdbcCampaignDao;
import com.sam.model.Campaign;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JdbcCampaignDaoTest extends BaseDaoTest {

    private static final Campaign CAMPAIGN_1 = new Campaign(1, "Un-titled Call of Cathulu", "A call of Cathulu campaign where there is a time loop set in 2020");
    private static final Campaign CAMPAIGN_2 = new Campaign(2,"The War Council", "The evil half of the world spanning campaign");
    private static final Campaign CAMPAIGN_3 = new Campaign(3, "The Adventure\'s Guild", "The good half of the world spanning campaign");

    private JdbcCampaignDao sut;
    private Campaign testCampaign;

    @Before
    public void setup(){
        sut = new JdbcCampaignDao(dataSource);
        testCampaign = new Campaign(4, "test campaign", "this is a test campaign");
    }

    @Test
    public void testGetCampaigns() {
        List<Campaign> expectedCampaigns = Arrays.asList(CAMPAIGN_1, CAMPAIGN_2, CAMPAIGN_3);
        List<Campaign> actualCampaigns = sut.getCampaigns();

        assertCampaignsMatchList(expectedCampaigns, actualCampaigns);
    }

    @Test
    public void testGetCampaignsByPerson() {
        List<Campaign> expectedCampaigns = Arrays.asList(CAMPAIGN_1, CAMPAIGN_2);
        List<Campaign> actualCampaigns = sut.getCampaignsByPerson(1);

        assertCampaignsMatchList(expectedCampaigns, actualCampaigns);
    }

    @Test
    public void testGetCampaignById() {
        assertCampaignsMatch(CAMPAIGN_1, sut.getCampaignById(1));
        assertCampaignsMatch(CAMPAIGN_2, sut.getCampaignById(2));
        assertCampaignsMatch(CAMPAIGN_3, sut.getCampaignById(3));
    }

    @Test
    public void testAddCampaign() {
        Campaign actualCampaign = sut.addCampaign(new Campaign(-1,"test campaign", "this is a test campaign"));
        assertCampaignsMatch(testCampaign, actualCampaign);
    }

    @Test
    public void testAddPlayerToCampaign() {
        List<Campaign> expectedCampaigns = Arrays.asList(CAMPAIGN_1, CAMPAIGN_2, CAMPAIGN_3);
        sut.addPlayerToCampaign(3,1);
        List<Campaign> actualCampaigns = sut.getCampaignsByPerson(1);

        assertCampaignsMatchList(expectedCampaigns, actualCampaigns);
    }

    @Test
    public void testUpdateCampaign() {
        testCampaign.setCampaignId(1);
        sut.updateCampaign(testCampaign);
        assertCampaignsMatch(testCampaign, sut.getCampaignById(1));
    }

    @Test
    public void testDeleteCampaign() {
        sut.deleteCampaign(3);
        Assert.assertNull(sut.getCampaignById(3));
    }

    private void assertCampaignsMatchList(List<Campaign> expected, List<Campaign> actual){
        for(int i = 0; i < actual.size(); i++){
            assertCampaignsMatch(expected.get(i), actual.get(i));
        }
    }

    private void assertCampaignsMatch(Campaign expected, Campaign actual){
        Assert.assertEquals(expected.getCampaignId(), actual.getCampaignId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }
}