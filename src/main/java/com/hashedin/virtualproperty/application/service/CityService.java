package com.hashedin.virtualproperty.application.service;

import com.hashedin.virtualproperty.application.entities.CityDetails;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.repository.CityDetailsRepo;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityDetailsRepo cityDetailsRepo;

    public CityService(CityDetailsRepo cityDetailsRepo) {
        this.cityDetailsRepo = cityDetailsRepo;
    }

    public String addCityDetails(CityDetails cityDetails)
    {
        CityDetails cityDetails1=cityDetailsRepo.getCityDetails(cityDetails.getCityName());
        if(cityDetails1!=null)
        throw new CustomException("City is already present");
        cityDetailsRepo.save(cityDetails);
        return "saved details of city "+cityDetails.getCityName();
    }

    public CityDetails getCityDetails(String city)  {
        CityDetails cityDetails=cityDetailsRepo.getCityDetails(city);
        if(cityDetails!=null)
            return cityDetails;
        else
            throw new CustomException("No details found of city "+city);
    }

}
