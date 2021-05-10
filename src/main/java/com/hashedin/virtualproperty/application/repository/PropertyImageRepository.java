package com.hashedin.virtualproperty.application.repository;

import com.hashedin.virtualproperty.application.entities.PropertyImage;
import com.hashedin.virtualproperty.application.response.FileResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PropertyImageRepository extends CrudRepository<PropertyImage, String> {
  @Query(value = "select p.url as url, p.publicId as publicId from PropertyImage p where p.property.propertyId = ?1")
  List<Map> findAllByPropertyId(Integer propertyId);
}
