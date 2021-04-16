package com.hashedin.virtualproperty.application.service;

import com.hashedin.virtualproperty.application.entities.CityDetails;
import com.hashedin.virtualproperty.application.entities.Property;
import com.hashedin.virtualproperty.application.repository.CityDetailsRepo;
import com.hashedin.virtualproperty.application.repository.PropertyRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepo propertyRepo;

    public PropertyService(PropertyRepo propertyRepo) {
        this.propertyRepo = propertyRepo;
    }

    public String createProperty(Property property)
    {
        propertyRepo.save(property);
        return "Saved Property...";
    }

    public List<Property> getProperty() throws Exception {

          List<Property> propertyList=propertyRepo.getProperties();
          if(propertyList.isEmpty())
              throw new Exception("No Property Found....");
          return propertyList;

    }

    public List<Property> getPropertyByCityName(String city) throws Exception {

        List<Property> propertyList=propertyRepo.getPropertiesByCity(city);

        if(propertyList.isEmpty())
            throw new Exception("No Property Found in city "+city);
        return propertyList;
    }

    public List<Property> getPropertyByCityAndType(String city,String type) throws Exception {

        List<Property> propertyList=propertyRepo.getPropertiesByCityAndType(city,type);

        if(propertyList.isEmpty())
            throw new Exception("No Property Found in city "+city+" of type "+type);
        return propertyList;
    }

    public List<Property> getPropertyByStateAndType(String state,String type) throws Exception {

        List<Property> propertyList=propertyRepo.getPropertiesByStateAndType(state,type);

        if(propertyList.isEmpty())
            throw new Exception("No Property Found in city "+state+" of type "+type);
        return propertyList;
    }

    public List<Property> getPropertyByAddress(String address,String city,String state) throws Exception {

        List<Property> propertyList=propertyRepo.getPropertiesByAddress(address,city,state);

        if(propertyList.isEmpty())
            throw new Exception("No Property Found in address "+address+" in city "+city);
        return propertyList;
    }

    public List<Property> getPropertyByState(String state) throws Exception {

        List<Property> propertyList=propertyRepo.getPropertiesByState(state);

        if(propertyList.isEmpty())
            throw new Exception("No Property Found in state "+state);
        return propertyList;
    }

    public Property getPropertyById(Integer propertyId) throws Exception {

        Property property=propertyRepo.getPropertiesById(propertyId);

        if(property==null)
            throw new Exception("No Property Found with id "+propertyId);
        return property;
    }

    public List<Property> getPropertyByBudget(int minPrice,int maxPrice) throws Exception {

        List<Property> property=propertyRepo.getPropertiesByBudget(minPrice,maxPrice);

        if(property.isEmpty())
            throw new Exception("No Property Found from budget "+minPrice+" to "+maxPrice);
        return property;
    }

    public List<Property> getPropertyByMaxPrice(int maxPrice) throws Exception {

        List<Property> property=propertyRepo.getPropertiesByMaxPrice(maxPrice);

        if(property.isEmpty())
            throw new Exception("No Property Found less than "+maxPrice);
        return property;
    }



}
