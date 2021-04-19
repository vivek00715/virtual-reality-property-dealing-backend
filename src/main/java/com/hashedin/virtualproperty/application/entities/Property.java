package com.hashedin.virtualproperty.application.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name="property")
@Entity
@Data
public class Property
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer propertyId;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false, unique = true)
    private String ownerEmail;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int area;

    @Column(nullable = true)
    private int bhk;

    @Column(nullable = false)
    private int builtYear;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int floors;

    @Column(nullable = false)
    private int bedrooms;

    @Column(nullable = false)
    private int bathrooms;

    @Column(nullable = false)
    private int pinCode;

    public Property()
    {

    }

    public Property(Integer propertyId, String city, String state, String address, String type, String purpose, String ownerEmail, String description, int area, int bhk, int builtYear, int price, int floors, int bedrooms, int bathrooms, int pinCode) {
        this.propertyId = propertyId;
        this.city = city;
        this.state = state;
        this.address = address;
        this.type = type;
        this.purpose = purpose;
        this.ownerEmail = ownerEmail;
        this.description = description;
        this.area = area;
        this.bhk = bhk;
        this.builtYear = builtYear;
        this.price = price;
        this.floors = floors;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.pinCode = pinCode;
    }
}
