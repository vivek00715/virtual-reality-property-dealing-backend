package com.hashedin.virtualproperty.application.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.response.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileStorageService {

  @Autowired AuthService authService;

  private final Cloudinary cloudinary;

  public FileStorageService() {
    this.cloudinary =
        new Cloudinary(
            ObjectUtils.asMap(
                "cloud_name", "hashedhomes",
                "api_key", "878983354313723",
                "api_secret", "NnWEsUkHoEbLjFuUvJ4_DoA5Jy0"));
  }

  public FileResponse storeFile(MultipartFile file, String token) throws IOException {
    // returns URL of the file stored
    // validate tokeng
    String userEmail = authService.getUserEmailFromToken(token);
    // each user's file will be stored in separate folder so that it can be easy to fetch
    Map response =
        this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
    System.out.println(response.toString());
    return new FileResponse(response.get("secure_url"), response.get("public_id"));
  }

  public FileResponse[] storeMultipleFiles(MultipartFile[] files, String token) throws IOException {
    FileResponse[] result = new FileResponse[files.length];
    for (int i = 0; i < files.length; i++) {
      result[i] = storeFile(files[i], token);
    }
    return result;
  }

  public void deleteFile(String publicId) throws Exception {
    System.out.println("DELETING IMAGE WITH ID: " + publicId);
    ApiResponse searchResult =
        this.cloudinary
            .search()
            .expression("public_id:" + publicId)
            .execute();
    if (searchResult.isEmpty()) {
      throw new CustomException("Image not found");
    }
    this.cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
  }
}
