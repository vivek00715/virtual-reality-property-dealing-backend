package com.hashedin.virtualproperty.application.repository;

import com.hashedin.virtualproperty.application.entities.PropertyNearbyFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyNearbyFacilitiesRepo extends CrudRepository<PropertyNearbyFacilities,String>, JpaRepository<PropertyNearbyFacilities,String> {
}
