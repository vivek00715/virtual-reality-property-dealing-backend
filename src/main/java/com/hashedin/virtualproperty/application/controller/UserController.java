package com.hashedin.virtualproperty.application.controller;

import com.hashedin.virtualproperty.application.entities.User;
import com.hashedin.virtualproperty.application.request.UserUpdateRequest;
import com.hashedin.virtualproperty.application.response.UserResponse;
import com.hashedin.virtualproperty.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{userEmail}")
    public UserResponse getUserByID(@PathVariable String userEmail , @RequestHeader(name="Authorization") String token){
        return userService.getUserDetail(userEmail , token);
    }

    @PutMapping(value="/user/")
    public UserResponse updateUserById(@RequestBody UserUpdateRequest user, @RequestHeader(name="Authorization") String token){
        return userService.updateUserById(user , token);
    }
}
