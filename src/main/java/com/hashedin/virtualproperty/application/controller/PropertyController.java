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

    @PostMapping(value="/property")
    public Property post(@RequestBody Property property,@RequestHeader (name="Authorization") String token)
    {
            System.out.println("hit post...");
//        System.out.println(token);
            return propertyService.createProperty(property,token);
    }

    @GetMapping(value="/property")
    public List<Property> get(
            @RequestParam(required=false,defaultValue = "0")int minPrice,
            @RequestParam(required=false,defaultValue = "2147483647")int maxPrice,
            @RequestParam(required = false,defaultValue = "")String street,
            @RequestParam(required = false,defaultValue = "")String city,
            @RequestParam(required = false,defaultValue = "")String state,
            @RequestParam(required = false,defaultValue = "")String type,
            @RequestParam(required = false,defaultValue = "")String purpose
    )
    {
        System.out.println(minPrice+" "+maxPrice+" "+street+" "+city+" "+state+" "+type+" "+purpose);
        return propertyService.getProperty(minPrice,maxPrice,street,city,state,
                                            type,purpose);
    }

    @GetMapping(value="property/{propertyId}")
    public Property getById(@PathVariable Integer propertyId)
    {
        return propertyService.getPropertyById(propertyId);
    }

    @GetMapping(value="owner/{ownerEmail}/property")
    public List<Property> getOwnerProperty(@PathVariable String ownerEmail)
    {
        return propertyService.getOwnerProperty(ownerEmail);
    }


//    @GetMapping(value="property/all")
//    public List<Property> gettingAllProperty()
//    {
//            return propertyService.getProperty();
//    }
//    @GetMapping(value="property/city/{city}")
//    public List<Property> gettingAllPropertiesByCityName(@PathVariable String city)
//    {
//            return propertyService.getPropertyByCityName(city);
//    }



//    @GetMapping(value="property/city-type/{city}/{type}")
//    public List<Property> gettingAllPropertiesByCityNameAndType(@PathVariable String city,@PathVariable String type)
//    {
//            return propertyService.getPropertyByCityAndType(city,type);
//    }
//
//    @GetMapping(value="property/state-type/{state}/{type}")
//    public List<Property> gettingAllPropertiesByStateNameAndType(@PathVariable String state,@PathVariable String type)
//    {
//            return propertyService.getPropertyByStateAndType(state,type);
//    }
//
//    @GetMapping(value="property/address/{address}/{city}/{state}")
//    public List<Property> gettingAllPropertiesByCityNameAndType(@PathVariable String address,@PathVariable String city,@PathVariable String state)
//    {
//            return propertyService.getPropertyByAddress(address,city,state);
//    }
//
//    @GetMapping(value="property/state/{state}")
//    public List<Property> gettingAllPropertiesByState(@PathVariable String state)
//    {
//            return propertyService.getPropertyByState(state);
//    }
//    @GetMapping(value="property/budget/{price}")
//    public List<Property> gettingPropertyByBudget(@PathVariable("price") int maxPrice)
//    {
//            return propertyService.getPropertyByMaxPrice(maxPrice);
//    }


}
