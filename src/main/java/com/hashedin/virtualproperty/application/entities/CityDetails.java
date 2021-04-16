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

    private String cityName,description;

}
