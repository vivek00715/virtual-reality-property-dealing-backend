package com.hashedin.virtualproperty.application.controller;

import com.hashedin.virtualproperty.application.entities.CityDetails;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.response.CityResponse;
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
    public CityResponse addingNewCity(@RequestBody CityDetails cityDetails)
    {
            return cityService.addCityDetails(cityDetails);
    }

    @GetMapping(value="city/{cityName}")
    public CityResponse gettingCityDetails(@PathVariable String cityName){

            return cityService.getCityDetails(cityName);
    }

}
