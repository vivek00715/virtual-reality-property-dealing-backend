package com.hashedin.virtualproperty.application.repository;

import com.hashedin.virtualproperty.application.entities.CityDetails;
import com.hashedin.virtualproperty.application.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityDetailsRepo extends CrudRepository<CityDetails,Integer>, JpaRepository<CityDetails,Integer> {

    @Query("select c from CityDetails c where c.cityName=?1")
    CityDetails getCityDetails(String cityName);


}
