package com.hashedin.virtualproperty.application.controller;


import com.hashedin.virtualproperty.application.entities.Property;
import com.hashedin.virtualproperty.application.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyController
{
    @Autowired
    private PropertyService propertyService;

    @PostMapping(value="property/create")
    public Property addingNewProperty(@RequestBody Property property)
    {
            System.out.println("hit post...");
            return propertyService.createProperty(property);
    }

    @GetMapping(value="property/all")
    public List<Property> gettingAllProperty()
    {
            return propertyService.getProperty();
    }

    @GetMapping(value="property/city/{city}")
    public List<Property> gettingAllPropertiesByCityName(@PathVariable String city)
    {
            return propertyService.getPropertyByCityName(city);
    }

    @GetMapping(value="property/city-type/{city}/{type}")
    public List<Property> gettingAllPropertiesByCityNameAndType(@PathVariable String city,@PathVariable String type)
    {
            return propertyService.getPropertyByCityAndType(city,type);
    }

    @GetMapping(value="property/state-type/{state}/{type}")
    public List<Property> gettingAllPropertiesByStateNameAndType(@PathVariable String state,@PathVariable String type)
    {
            return propertyService.getPropertyByStateAndType(state,type);
    }

    @GetMapping(value="property/address/{address}/{city}/{state}")
    public List<Property> gettingAllPropertiesByCityNameAndType(@PathVariable String address,@PathVariable String city,@PathVariable String state)
    {
            return propertyService.getPropertyByAddress(address,city,state);
    }

    @GetMapping(value="property/state/{state}")
    public List<Property> gettingAllPropertiesByState(@PathVariable String state)
    {
            return propertyService.getPropertyByState(state);
    }

    @GetMapping(value="property/id/{propertyId}")
    public Property gettingPropertyById(@PathVariable Integer propertyId)
    {
            return propertyService.getPropertyById(propertyId);
    }

    @GetMapping(value="property/budget/{price}")
    public List<Property> gettingPropertyByBudget(@PathVariable("price") int maxPrice)
    {
            return propertyService.getPropertyByMaxPrice(maxPrice);
    }


}
