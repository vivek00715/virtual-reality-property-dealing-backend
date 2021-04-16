package com.hashedin.virtualproperty.application.controller;

import com.hashedin.virtualproperty.application.entities.CityDetails;
import com.hashedin.virtualproperty.application.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController
{
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(value="city/create")
    public ResponseEntity addingNewCity(@RequestBody CityDetails cityDetails)
    {
        try{
            String returnedvalue=cityService.addCityDetails(cityDetails);
            return new ResponseEntity(returnedvalue, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="city/{cityName}")
    public ResponseEntity gettingCityDetails(@PathVariable String cityName)
    {
        try{
            CityDetails cityDetails=cityService.getCityDetails(cityName);
            return new ResponseEntity(cityDetails, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
