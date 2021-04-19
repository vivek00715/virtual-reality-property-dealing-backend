package com.hashedin.virtualproperty.application.service;

import com.hashedin.virtualproperty.application.entities.CityDetails;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.repository.CityDetailsRepo;
import com.hashedin.virtualproperty.application.response.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityDetailsRepo cityDetailsRepo;

    public CityResponse addCityDetails(CityDetails cityDetails)
    {
        CityDetails cityDetails1=cityDetailsRepo.getCityDetails(cityDetails.getCityName());
        if(cityDetails1!=null)
        throw new CustomException("City is already present");
        cityDetailsRepo.save(cityDetails);
        return new CityResponse(cityDetails.getCityId(),cityDetails.getCityName(),cityDetails.getDescription());
    }

    public CityResponse getCityDetails(String city)  {
        CityDetails cityDetails=cityDetailsRepo.getCityDetails(city);
        if(cityDetails!=null)
            return new CityResponse(cityDetails.getCityId(),cityDetails.getCityName(),cityDetails.getDescription());
        else
            throw new CustomException("No details found of city "+city);
    }

}
