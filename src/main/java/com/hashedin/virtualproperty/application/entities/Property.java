package com.hashedin.virtualproperty.application.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name="property")
@Entity
@Data
public class Property
{
    @Id
    private Integer propertyId;
    private String city,state,address,bedrooms,bathrooms,type,purpose,ownerEmail,description,pinCode;
    private int area,bhk,builtYear,price,floors;
}
