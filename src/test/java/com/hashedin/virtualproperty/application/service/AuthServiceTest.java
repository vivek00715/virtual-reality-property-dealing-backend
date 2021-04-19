package com.hashedin.virtualproperty.application.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.hashedin.virtualproperty.application.entities.User;
import com.hashedin.virtualproperty.application.repository.UserRepository;
import com.hashedin.virtualproperty.application.response.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void checkingCorrectUserLogin(){
//        String password = "123456789";
//        String hashedPassword = BCrypt.withDefaults().hashToString(10, password.toCharArray());
//        User user = new User("xyz@gmail.com" , hashedPassword , "xyz" , "9666314745" , "hashedin university");
//        Optional<User> user1 = Optional.ofNullable(user);
//        Mockito.when(userRepository.findById("xyz@gmail.com")).thenReturn(user1);
//
//        AuthResponse authResponse =  authService.loginUser("xyz@gmail.com" , "123456789");
//        assertEquals("xyz@gmail.com" , authResponse.email);
//        assertEquals("xyz" , authResponse.name);
//        assertEquals("9666314745" , authResponse.mobile);
//        assertEquals("hashedin university" , authResponse.address);
//        assertTrue(authResponse.token.length() > 0);
//
//
//    }
}