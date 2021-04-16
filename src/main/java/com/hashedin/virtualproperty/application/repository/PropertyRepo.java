package com.hashedin.virtualproperty.application.repository;

import com.hashedin.virtualproperty.application.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepo extends CrudRepository<Property,Integer>, JpaRepository<Property,Integer> {

    @Query("select p from Property p")
    List<Property> getProperties();

    @Query("select p from Property p where p.city like %?1")
    List<Property> getPropertiesByCity(String city);

    @Query("select p from Property p where p.city like %?1 and p.type like %?2")
    List<Property> getPropertiesByCityAndType(String city,String type);

    @Query("select p from Property p where p.state like %?1 and p.type like %?2")
    List<Property> getPropertiesByStateAndType(String state,String type);

    @Query("select p from Property p where p.address like %?1 and p.city like %?2 and p.state like %?3")
    List<Property> getPropertiesByAddress(String address,String city,String state);

    @Query("select p from Property p where p.state like %?1")
    List<Property> getPropertiesByState(String state);

    @Query("select p from Property p where propertyId =?1")
    Property getPropertiesById(Integer id);

    @Query("select p from Property p where p.price>=?1 and p.price<=?2")
    List<Property> getPropertiesByBudget(int minPrice,int maxPrice);

    @Query("select p from Property p where p.price<=?1")
    List<Property> getPropertiesByMaxPrice(int maxPrice);


}
