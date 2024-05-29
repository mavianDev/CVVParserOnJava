package com.pet.api.models;

public class LocationModel {
    private String state;
    private String country;
    private int latestCases;

    @Override
    public String toString() {
        return "LocationModel{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestCases=" + latestCases +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestCases() {
        return latestCases;
    }

    public void setLatestCases(int latestCases) {
        this.latestCases = latestCases;
    }
}
