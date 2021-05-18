package com.hashedin.virtualproperty.application.service;


import com.hashedin.virtualproperty.application.entities.User;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.repository.UserRepository;
import com.hashedin.virtualproperty.application.request.UserUpdateRequest;
import com.hashedin.virtualproperty.application.response.FileResponse;
import com.hashedin.virtualproperty.application.response.UserResponse;
import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private FileStorageService fileStorageService;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserResponse getUserDetail(String email, String token) {
        User u = this.authService.getUserFromToken(token);
        this.logger.info("SENDING INFORMATION OF USER WITH EMAIL " + email);

        return new UserResponse(u.getName() , u.getAddress() , u.getUserImage() , u.getMobile() , u.getEmail(), u.isAdministrator());
    }

    public UserResponse addImage(MultipartFile image, String token) throws IOException {
        User user = this.authService.getUserFromToken(token);
        this.logger.info("Updating image for user: " + user.getUserImage());
        FileResponse file = this.fileStorageService.storeFile(image);
        user.setUserImage(file.url);
        User savedUser = this.userRepository.save(user);
        return new UserResponse(
                savedUser.getName(),
                savedUser.getAddress(),
                savedUser.getUserImage(),
                savedUser.getMobile(),
                savedUser.getEmail(),
                savedUser.isAdministrator()
        );
    }

    public UserResponse updateUserById(UserUpdateRequest user, String token) {
        User u1 = this.authService.getUserFromToken(token);
        u1.setName(user.name);
        u1.setAddress(user.address);
        u1.setMobile(user.mobile);
        User u = userRepository.save(u1);
        this.logger.info("UPDATING USER WITH EMAIL " + u1.getEmail());

        return new UserResponse(u.getName() , u.getAddress() , u.getUserImage() , u.getMobile() , u.getEmail(), u.isAdministrator());

    }
}
