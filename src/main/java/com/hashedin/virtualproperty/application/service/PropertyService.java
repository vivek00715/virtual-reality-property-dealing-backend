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

    @Autowired
    private AuthService authService;

    public Property createProperty(Property property,String token)
    {
        property.setOwnerEmail(authService.getUserEmailFromToken(token));
        return propertyRepo.save(property);
    }

    public List<Property> getProperty(int minPrice,int maxPrice,String street,String city,String state,String type,String purpose)  {

        List<Property> propertyList=propertyRepo.getProperties(minPrice,maxPrice,street,city,state,
                                                               type,purpose);
          if(propertyList.isEmpty())
              throw new CustomException("No Property Found....");
          return propertyList;

    }
    public Property getPropertyById(Integer propertyId)  {

        Property property=propertyRepo.getPropertiesById(propertyId);

        if(property==null)
            throw new CustomException("No Property Found with id "+propertyId);
        return property;
    }
    public List<Property> getOwnerProperty(String email)  {

        List<Property> property=propertyRepo.getOwnerProperty(email);

        if(property==null)
            throw new CustomException("No Property Found ");
        return property;
    }

    public Property deleteProperty(Integer propertyId)
    {
        if(propertyRepo.findById(propertyId).isPresent())
        {
            Property property1=propertyRepo.getPropertiesById(propertyId);
            propertyRepo.deleteById(propertyId);
            return property1;
        }
        else
            throw new CustomException("This property is already not present");

    }

    public Property editProperty(Property property,Integer id)
    {
        Property property1=propertyRepo.getPropertiesById(id);

        property1.setAddress(property.getAddress());
        property1.setArea(property.getArea());
        property1.setBathrooms(property.getBathrooms());
        property1.setBedrooms(property.getBedrooms());
        property1.setBhk(property.getBhk());
        property1.setBuiltYear(property.getBuiltYear());
        property1.setCity(property.getCity());
        property1.setFloors(property.getFloors());
        property1.setState(property1.getState());
        property1.setDescription(property.getDescription());
        property1.setPurpose(property.getPurpose());
        property1.setType(property.getType());
        property1.setPrice(property.getPrice());
        property1.setPinCode(property.getPinCode());
        propertyRepo.save(property1);
        return property1;
    }

//    public List<Property> getPropertyByCityName(String city) {
//
//        List<Property> propertyList=propertyRepo.getPropertiesByCity(city);
//
//        if(propertyList.isEmpty())
//            throw new CustomException("No Property Found in city "+city);
//        return propertyList;
//    }
//
//    public List<Property> getPropertyByCityAndType(String city,String type)  {
//
//        List<Property> propertyList=propertyRepo.getPropertiesByCityAndType(city,type);
//
//        if(propertyList.isEmpty())
//            throw new CustomException("No Property Found in city "+city+" of type "+type);
//        return propertyList;
//    }
//
//    public List<Property> getPropertyByStateAndType(String state,String type)  {
//
//        List<Property> propertyList=propertyRepo.getPropertiesByStateAndType(state,type);
//
//        if(propertyList.isEmpty())
//            throw new CustomException("No Property Found in city "+state+" of type "+type);
//        return propertyList;
//    }
//
//    public List<Property> getPropertyByAddress(String address,String city,String state)  {
//
//        List<Property> propertyList=propertyRepo.getPropertiesByAddress(address,city,state);
//
//        if(propertyList.isEmpty())
//            throw new CustomException("No Property Found in address "+address+" in city "+city);
//        return propertyList;
//    }
//
//    public List<Property> getPropertyByState(String state)  {
//
//        List<Property> propertyList=propertyRepo.getPropertiesByState(state);
//
//        if(propertyList.isEmpty())
//            throw new CustomException("No Property Found in state "+state);
//        return propertyList;
//    }
//
//    public List<Property> getPropertyByBudget(int minPrice,int maxPrice) {
//
//        List<Property> property=propertyRepo.getPropertiesByBudget(minPrice,maxPrice);
//
//        if(property.isEmpty())
//            throw new CustomException("No Property Found from budget "+minPrice+" to "+maxPrice);
//        return property;
//    }
//
//    public List<Property> getPropertyByMaxPrice(int maxPrice)  {
//
//        List<Property> property=propertyRepo.getPropertiesByMaxPrice(maxPrice);
//
//        if(property.isEmpty())
//            throw new CustomException("No Property Found less than "+maxPrice);
//        return property;
//    }



}
