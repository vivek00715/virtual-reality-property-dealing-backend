package com.hashedin.virtualproperty.application.response;

import lombok.Data;

import java.util.List;

@Data
public class PropertyResponse
{
    private Integer propertyId;

    private String city;

    private String state;

    private String address;

    private String type;

    private String purpose;

    private String ownerEmail;

    private String description;

    private int area;

    private int bhk;

    private int builtYear;

    private int price;

    private int floors;

    private int bedrooms;

    private int bathrooms;

    private int pinCode;

    private List<FileResponse> images;

    public PropertyResponse(Integer propertyId, String city, String state, String address, String type, String purpose, String ownerEmail, String description, int area, int bhk, int builtYear, int price, int floors, int bedrooms, int bathrooms, int pinCode, List<FileResponse> propertyImages) {
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
        this.images = propertyImages;
    }
}
