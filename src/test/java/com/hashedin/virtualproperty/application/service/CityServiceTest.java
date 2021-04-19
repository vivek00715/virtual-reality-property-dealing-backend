package com.hashedin.virtualproperty.application.service;

import com.hashedin.virtualproperty.application.entities.CityDetails;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.repository.CityDetailsRepo;
import com.hashedin.virtualproperty.application.response.CityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CityServiceTest {

    @Mock
    private CityDetailsRepo cityDetailsRepo;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCorrectAddCityDetails(){
        CityDetails cityDetails = new CityDetails(1 , "lucknow" , "City Of Nawabs");
        Mockito.when(cityDetailsRepo.getCityDetails("lucknow")).thenReturn(null);
        Mockito.when(cityDetailsRepo.save(cityDetails)).thenReturn(cityDetails);
        CityResponse cityResponse = new CityResponse(1 , "lucknow" , "City Of Nawabs");
        CityResponse cityResponse1 = cityService.addCityDetails(cityDetails);
        assertEquals(cityResponse.cityId , cityResponse1.cityId);
        assertEquals(cityResponse.cityName , cityResponse1.cityName);
        assertEquals(cityResponse.description , cityResponse1.description);
    }

    @Test
    void testCorrectAddCityDetailsWhenCityIsAlreadyPresent(){
        CityDetails cityDetails = new CityDetails(1 , "lucknow" , "City Of Nawabs");
        Mockito.when(cityDetailsRepo.getCityDetails("lucknow")).thenReturn(cityDetails);
        assertThrows(CustomException.class , ()->cityService.addCityDetails(cityDetails));
    }

    @Test
    void testGetCityDetails(){
        CityDetails cityDetails = new CityDetails(1 , "lucknow" , "City Of Nawabs");
        Mockito.when(cityDetailsRepo.getCityDetails("lucknow")).thenReturn(cityDetails);
        CityResponse cityResponse = new CityResponse(1 , "lucknow" , "City Of Nawabs");
        CityResponse cityResponse1 = cityService.getCityDetails("lucknow");
        assertEquals(cityResponse.cityId , cityResponse1.cityId);
        assertEquals(cityResponse.cityName , cityResponse1.cityName);
        assertEquals(cityResponse.description , cityResponse1.description);
    }

    @Test
    void testGetCityDetailsWhenCityIsNotPresent(){
        Mockito.when(cityDetailsRepo.getCityDetails("lucknow")).thenReturn(null);
        assertThrows(CustomException.class , ()->cityService.getCityDetails("lucknow"));
    }

}