package com.hashedin.virtualproperty.application.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name="cityDetail")
@Entity
@Data
public class CityDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cityId;

    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private String description;

    public CityDetails()
    {

    }

    public CityDetails(int cityId, String cityName, String description) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.description = description;
    }

}
