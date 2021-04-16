package com.hashedin.virtualproperty.application.service;

import com.hashedin.virtualproperty.application.entities.Property;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepo propertyRepo;

    public String createProperty(Property property)
    {
        propertyRepo.save(property);
        return "Saved Property...";
    }

    public List<Property> getProperty()  {

          List<Property> propertyList=propertyRepo.getProperties();
          if(propertyList.isEmpty())
              throw new CustomException("No Property Found....");
          return propertyList;

    }

    public List<Property> getPropertyByCityName(String city) {

        List<Property> propertyList=propertyRepo.getPropertiesByCity(city);

        if(propertyList.isEmpty())
            throw new CustomException("No Property Found in city "+city);
        return propertyList;
    }

    public List<Property> getPropertyByCityAndType(String city,String type)  {

        List<Property> propertyList=propertyRepo.getPropertiesByCityAndType(city,type);

        if(propertyList.isEmpty())
            throw new CustomException("No Property Found in city "+city+" of type "+type);
        return propertyList;
    }

    public List<Property> getPropertyByStateAndType(String state,String type)  {

        List<Property> propertyList=propertyRepo.getPropertiesByStateAndType(state,type);

        if(propertyList.isEmpty())
            throw new CustomException("No Property Found in city "+state+" of type "+type);
        return propertyList;
    }

    public List<Property> getPropertyByAddress(String address,String city,String state)  {

        List<Property> propertyList=propertyRepo.getPropertiesByAddress(address,city,state);

        if(propertyList.isEmpty())
            throw new CustomException("No Property Found in address "+address+" in city "+city);
        return propertyList;
    }

    public List<Property> getPropertyByState(String state)  {

        List<Property> propertyList=propertyRepo.getPropertiesByState(state);

        if(propertyList.isEmpty())
            throw new CustomException("No Property Found in state "+state);
        return propertyList;
    }

    public Property getPropertyById(Integer propertyId)  {

        Property property=propertyRepo.getPropertiesById(propertyId);

        if(property==null)
            throw new CustomException("No Property Found with id "+propertyId);
        return property;
    }

    public List<Property> getPropertyByBudget(int minPrice,int maxPrice) {

        List<Property> property=propertyRepo.getPropertiesByBudget(minPrice,maxPrice);

        if(property.isEmpty())
            throw new CustomException("No Property Found from budget "+minPrice+" to "+maxPrice);
        return property;
    }

    public List<Property> getPropertyByMaxPrice(int maxPrice)  {

        List<Property> property=propertyRepo.getPropertiesByMaxPrice(maxPrice);

        if(property.isEmpty())
            throw new CustomException("No Property Found less than "+maxPrice);
        return property;
    }



}
