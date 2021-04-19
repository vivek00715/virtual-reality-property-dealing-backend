package com.hashedin.virtualproperty.application.response;

public class CityResponse {

    public int cityId;
    public String cityName;
    public String description;

    public CityResponse(int cityId, String cityName, String description) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.description = description;
    }
}
