package com.sam.model;

import java.time.LocalTime;
import java.util.Objects;

public class DayOfWeek {

    private int personId;
    private int campaignId;
    private String dayCode;
    private boolean isFree;
    private LocalTime startTime;
    private LocalTime endTime;

    public DayOfWeek(){}

    public DayOfWeek(int personId, int campaignId, String dayCode, boolean isFree, LocalTime startTime, LocalTime endTime) {
        this.personId = personId;
        this.campaignId = campaignId;
        this.dayCode = dayCode;
        this.isFree = isFree;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getDayCode() {
        return dayCode;
    }

    public void setDayCode(String dayCode) {
        this.dayCode = dayCode;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayOfWeek dayOfWeek = (DayOfWeek) o;
        return personId == dayOfWeek.personId && isFree == dayOfWeek.isFree && Objects.equals(dayCode, dayOfWeek.dayCode) && Objects.equals(startTime, dayOfWeek.startTime) && Objects.equals(endTime, dayOfWeek.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, dayCode, isFree, startTime, endTime);
    }

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "personId=" + personId +
                ", dayCode='" + dayCode + '\'' +
                ", isFree=" + isFree +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
