package com.hashedin.virtualproperty.application.controller;

import com.hashedin.virtualproperty.application.entities.CityDetails;
import com.hashedin.virtualproperty.application.entities.Property;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.service.CityService;
import com.hashedin.virtualproperty.application.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyController
{
    @Autowired
    private PropertyService propertyService;

    @PostMapping(value="property/create")
    public ResponseEntity addingNewProperty(@RequestBody Property property)
    {
        try{
            System.out.println("hit post...");
            String returnedvalue=propertyService.createProperty(property);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="property/all")
    public ResponseEntity gettingAllProperty()
    {
        try{
            List<Property> returnedvalue=propertyService.getProperty();
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="property/city/{city}")
    public ResponseEntity gettingAllPropertiesByCityName(@PathVariable String city)
    {
        try{
            List<Property> returnedvalue=propertyService.getPropertyByCityName(city);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="property/city-type/{city}/{type}")
    public ResponseEntity gettingAllPropertiesByCityNameAndType(@PathVariable String city,@PathVariable String type)
    {
        try{
            List<Property> returnedvalue=propertyService.getPropertyByCityAndType(city,type);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="property/state-type/{state}/{type}")
    public ResponseEntity gettingAllPropertiesByStateNameAndType(@PathVariable String state,@PathVariable String type)
    {
        try{
            List<Property> returnedvalue=propertyService.getPropertyByStateAndType(state,type);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="property/address/{address}/{city}/{state}")
    public ResponseEntity gettingAllPropertiesByCityNameAndType(@PathVariable String address,@PathVariable String city,@PathVariable String state)
    {
        try{
            List<Property> returnedvalue=propertyService.getPropertyByAddress(address,city,state);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="property/state/{state}")
    public ResponseEntity gettingAllPropertiesByState(@PathVariable String state)
    {
        try{
            List<Property> returnedvalue=propertyService.getPropertyByState(state);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="property/id/{propertyId}")
    public ResponseEntity gettingPropertyById(@PathVariable Integer propertyId)
    {
        try{
            Property returnedvalue=propertyService.getPropertyById(propertyId);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="property/budget/{price}")
    public ResponseEntity gettingPropertyByBudget(@PathVariable("price") int maxPrice)
    {
        try{
            List<Property> returnedvalue=propertyService.getPropertyByMaxPrice(maxPrice);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(CustomException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
