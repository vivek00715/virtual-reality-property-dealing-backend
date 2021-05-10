package com.hashedin.virtualproperty.application.repository;

import com.hashedin.virtualproperty.application.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepo extends CrudRepository<Property,Integer>, JpaRepository<Property,Integer> {

    @Query("select p from Property p where " +
            "p.price>=?1 and p.price<=?2 and " +
            "p.address like %?3 and p.city like %?4 and p.state like %?5 and " +
            "p.type like %?6 and p.purpose like %?7")
    List<Property> getProperties(int minPrice,int maxPrice,String address, String city,String state,
                                 String type,String purpose );

    @Query("select p from Property p where p.ownerEmail=?1")
     List<Property> getOwnerProperty(String email);

    @Query("select p from Property p where propertyId =?1")
     Property getPropertiesById(Integer id);
}
