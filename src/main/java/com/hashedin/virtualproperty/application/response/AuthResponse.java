package com.hashedin.virtualproperty.application.response;

public class AuthResponse {
    // Auth response will have email, name and jwt token
    public String email;
    public String name;
    public String token;
    public String address;
    public String mobile;

    public AuthResponse(String email, String name, String address, String mobile, String token){
        this.email = email;
        this.name = name;
        this.token = token;
        this.address = address;
        this.mobile = mobile;
    }
}
