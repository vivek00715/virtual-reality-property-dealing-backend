package com.hashedin.virtualproperty.application.service;


import com.hashedin.virtualproperty.application.entities.User;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.repository.UserRepository;
import com.hashedin.virtualproperty.application.response.UserResponse;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public UserResponse getUserDetail(String email, String token){
        authService.getUserEmailFromToken(token);
        Optional<User> user=  userRepository.findById(email);
        if(user.isEmpty()){
            throw new CustomException("user not found with given email");
        }
        User u = user.get();
        return new UserResponse(u.getName() , u.getAddress() , u.getUserImage() , u.getMobile() , u.getEmail());

    }
}
