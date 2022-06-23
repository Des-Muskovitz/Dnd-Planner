package model;

import java.util.Objects;

public class Campaign {

    private int campaignId;
    private String name;
    private String description;

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return campaignId == campaign.campaignId && Objects.equals(name, campaign.name) && Objects.equals(description, campaign.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignId, name, description);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaignId=" + campaignId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
