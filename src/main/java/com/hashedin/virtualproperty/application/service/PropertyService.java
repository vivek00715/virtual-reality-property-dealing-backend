package com.hashedin.virtualproperty.application.service;

import com.hashedin.virtualproperty.application.entities.Property;
import com.hashedin.virtualproperty.application.entities.PropertyImage;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.exceptions.UnauthorizedException;
import com.hashedin.virtualproperty.application.repository.PropertyImageRepository;
import com.hashedin.virtualproperty.application.repository.PropertyRepo;
import com.hashedin.virtualproperty.application.request.PropertyRequest;
import com.hashedin.virtualproperty.application.response.FileResponse;
import com.hashedin.virtualproperty.application.response.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PropertyService {

  @Autowired private PropertyRepo propertyRepo;

  @Autowired private AuthService authService;

  @Autowired private FileStorageService fileStorageService;

  @Autowired private PropertyImageRepository propertyImageRepository;

  public PropertyResponse createProperty(PropertyRequest propertyRequest, String token) {
    Property property = this.convertPropertyRequestToProperty(propertyRequest);
    property.setOwnerEmail(authService.getUserEmailFromToken(token));
    Property savedProperty = propertyRepo.save(property);
    return this.convertPropertyToPropertyResponse(savedProperty);
  }

  public List<PropertyResponse> getProperty(
      int minPrice,
      int maxPrice,
      String street,
      String city,
      String state,
      String type,
      String purpose) {

    List<Property> propertyList =
        propertyRepo.getProperties(minPrice, maxPrice, street, city, state, type, purpose);
    if (propertyList.isEmpty()) throw new CustomException("No Property Found....");
    ArrayList<PropertyResponse> response = new ArrayList<>();
    for (Property property : propertyList) {
      response.add(this.convertPropertyToPropertyResponse(property));
    }
    System.out.println("FOUND PROPERTIES: " + response.size());
    return response;
  }

  public PropertyResponse getPropertyById(Integer propertyId) {

    Property property = propertyRepo.getPropertiesById(propertyId);

    if (property == null) throw new CustomException("No Property Found with id " + propertyId);
    return this.convertPropertyToPropertyResponse(property);
  }

  public List<PropertyResponse> getOwnerProperty(String email) {

    List<Property> property = propertyRepo.getOwnerProperty(email);

    if (property == null) throw new CustomException("No Property Found ");
    ArrayList<PropertyResponse> response = new ArrayList<>();
    for (Property value : property) {
      response.add(this.convertPropertyToPropertyResponse(value));
    }
    return response;
  }

  public Property deleteProperty(Integer propertyId, String token) {
    String userEmail = this.authService.getUserEmailFromToken(token);
    Optional<Property> propertyOptional = propertyRepo.findById(propertyId);
    if (propertyOptional.isEmpty()) {
      throw new CustomException("Property not found");
    }
    Property property = propertyOptional.get();
    if (!property.getOwnerEmail().equalsIgnoreCase(userEmail)) {
      throw new UnauthorizedException("Unauthorized to delete property created by others");
    }
    propertyRepo.delete(property);
    // return the deleted property
    return property;
  }

  public PropertyResponse editProperty(PropertyRequest property, Integer id, String token) {
    String userEmail = this.authService.getUserEmailFromToken(token);
    Property property1 = propertyRepo.getPropertiesById(id);
    if (!property1.getOwnerEmail().equalsIgnoreCase(userEmail)) {
      // cannot edit property of others
      throw new UnauthorizedException("Unauthorized to perform this action");
    }
    property1.setAddress(property.getAddress());
    property1.setArea(property.getArea());
    property1.setBathrooms(property.getBathrooms());
    property1.setBedrooms(property.getBedrooms());
    property1.setBhk(property.getBhk());
    property1.setBuiltYear(property.getBuiltYear());
    property1.setCity(property.getCity());
    property1.setFloors(property.getFloors());
    property1.setState(property1.getState());
    property1.setDescription(property.getDescription());
    property1.setPurpose(property.getPurpose());
    property1.setType(property.getType());
    property1.setPrice(property.getPrice());
    property1.setPinCode(property.getPinCode());
    Property savedProperty = propertyRepo.save(property1);
    return this.convertPropertyToPropertyResponse(savedProperty);
  }

  public PropertyResponse addImage(Integer propertyId, MultipartFile image, String token)
      throws IOException {
    String userEmail = this.authService.getUserEmailFromToken(token);
    Optional<Property> propertyContainer = this.propertyRepo.findById(propertyId);
    if (propertyContainer.isEmpty()) {
      throw new CustomException("Invalid Property ID");
    }
    Property property = propertyContainer.get();
    if (!property.getOwnerEmail().equalsIgnoreCase(userEmail)) {
      throw new UnauthorizedException("Unauthorized to perform this action");
    }
    FileResponse response = this.fileStorageService.storeFile(image, token);
    PropertyImage propertyImage = new PropertyImage(response.id, response.url, property);
    this.propertyImageRepository.save(propertyImage);
    return this.convertPropertyToPropertyResponse(property);
  }

  public PropertyResponse deleteImage(String imageId, String token) throws Exception {
    String userEmail = this.authService.getUserEmailFromToken(token);
    Optional<PropertyImage> propertyImageOptional = this.propertyImageRepository.findById(imageId);
    if(propertyImageOptional.isEmpty()){
      throw new CustomException("Image with id " + imageId + " not found");
    }
    PropertyImage image = propertyImageOptional.get();
    Property property = image.getProperty();
    if(!property.getOwnerEmail().equalsIgnoreCase(userEmail)){
      throw new CustomException("Unauthorized to delete other user's images");
    }
    this.fileStorageService.deleteFile(imageId);
    this.propertyImageRepository.delete(image);
    return this.convertPropertyToPropertyResponse(property);
  }

  private PropertyResponse convertPropertyToPropertyResponse(Property property) {
    System.out.println("Converting Property to PropertyResponse");
    List<Map> imagesFromDb =
        this.propertyImageRepository.findAllByPropertyId(property.getPropertyId());

    ArrayList<FileResponse> images = new ArrayList<>();
    for (Map map : imagesFromDb) {
      images.add(new FileResponse(map.get("url"), map.get("publicId")));
    }
    System.out.println("Images in Property: " + images.size());
    PropertyResponse response =
        new PropertyResponse(
            property.getPropertyId(),
            property.getCity(),
            property.getState(),
            property.getAddress(),
            property.getType(),
            property.getPurpose(),
            property.getOwnerEmail(),
            property.getDescription(),
            property.getArea(),
            property.getBhk(),
            property.getBuiltYear(),
            property.getPrice(),
            property.getFloors(),
            property.getBedrooms(),
            property.getBathrooms(),
            property.getPinCode(),
            images);
    System.out.println("Converted Property to Response");
    System.out.println(response);
    return response;
  }

  private Property convertPropertyRequestToProperty(PropertyRequest request) {
    System.out.println("CONVERTING " + request);
    return new Property(
        request.city,
        request.state,
        request.address,
        request.type,
        request.purpose,
        request.description,
        request.area,
        request.bhk,
        request.builtYear,
        request.price,
        request.floors,
        request.bedrooms,
        request.bathrooms,
        request.pinCode);
  }
}
