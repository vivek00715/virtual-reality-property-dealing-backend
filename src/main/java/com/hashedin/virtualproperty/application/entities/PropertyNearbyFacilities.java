package com.hashedin.virtualproperty.application.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="property_nearby_facilities")
@Entity
@Data
public class PropertyNearbyFacilities {
    @Id
    private String pincode;
    private String metro,station,airport,school,hospital;
}
