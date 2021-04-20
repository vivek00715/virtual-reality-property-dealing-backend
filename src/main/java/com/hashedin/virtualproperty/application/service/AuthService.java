package com.hashedin.virtualproperty.application.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hashedin.virtualproperty.application.entities.User;
import com.hashedin.virtualproperty.application.exceptions.CustomException;
import com.hashedin.virtualproperty.application.exceptions.InvalidRequest;
import com.hashedin.virtualproperty.application.exceptions.UnauthorizedException;
import com.hashedin.virtualproperty.application.repository.UserRepository;
import com.hashedin.virtualproperty.application.response.AuthResponse;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse loginUser(String email, String password) {
        this.logger.info(email + " login request");
        this.validateData(email, password);

        // find if user is present in database to authenticate
        Optional<User> userContainer = this.userRepository.findById(email);
        if (userContainer.isEmpty()) {
            throw new CustomException("User with email " + email + " does not exist");
        }

        User user = userContainer.get();
        // user exists in database, check if login password is correct
        String hashedPassword = user.getPassword();
        if (!BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified) {
            // invalid credentials for user
            throw new CustomException("Invalid login credentials provided");
        }
        // user has entered correct details, generate token and send response
        String token = this.generateJWTToken(user.getEmail());
        return new AuthResponse(user.getEmail(), user.getName(), user.getAddress(), user.getMobile(), token);

    }

    public AuthResponse signupUser(String email, String password, String name, String mobile, String address) {
        this.logger.info(email + " signup request");
        this.validateSignupData(email, password, name, mobile, address);
        // details are valid till now

        if (this.userRepository.countOfUsersByEmailOrMobile(email, mobile) != 0) {
            // some other user has taken email or mobile, hence unique constraint will be violated
            throw new CustomException("Email or mobile is not unique");
        }
        // sign up user
        String hashedPassword = BCrypt.withDefaults().hashToString(10, password.toCharArray());
        User savedUser = this.userRepository.save(new User(email, hashedPassword, name, mobile, address));
        String token = generateJWTToken(email);

        // return the response
        return new AuthResponse(savedUser.getEmail(), savedUser.getName(), savedUser.getAddress(), savedUser.getMobile(), token);

    }

    // helper methods

    private void validateSignupData(String email, String password, String name, String mobile, String address) {
        this.validateData(email, password);
        ArrayList<String> invalidData = new ArrayList<String>();
        if (name == null || name.length() < 3) {
            invalidData.add("Name should be atleast 3 characters");
        }
        if (mobile == null) {
            invalidData.add("Invalid mobile number");
        } else {
            // check if mobile number is valid
            try {
                Pattern phoneNumberPattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");
                Matcher phoneNumberMatcher = phoneNumberPattern.matcher(mobile);
                if (!phoneNumberMatcher.find() && phoneNumberMatcher.group().equals(mobile)) {
                    // invalid mobile number
                    throw new Exception("No match found");
                }
            } catch (Exception e) {
                invalidData.add("Invalid mobile number");
            }
        }
        if (address == null || address.length() < 10) {
            invalidData.add("Address should be atleast 10 characters");
        }

        if (invalidData.size() > 0) {
            StringBuilder error = new StringBuilder();
            for (var message : invalidData) {
                error.append(message).append(", ");
            }
            throw new InvalidRequest(error.substring(0, error.length() - 2)); // remove trailing comma
        }
    }

    private void validateData(String email, String password) {
        ArrayList<String> invalidData = new ArrayList<String>();
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
            invalidData.add("Email is invalid");
        }
        if (password == null || password.length() < 6) {
            invalidData.add("Length of Password should be atleast 6 characters");
        }
        if (invalidData.size() > 0) {
            StringBuilder error = new StringBuilder();
            for (var message : invalidData) {
                error.append(message).append(", ");
            }
            throw new InvalidRequest(error.substring(0, error.length() - 2)); // remove trailing comma
        }
    }

    private String generateJWTToken(String email) {
        Algorithm algorithmHS = Algorithm.HMAC256(this.getJwtSecret());
        return JWT.create()
                .withIssuer(this.getIssuer())
                .withClaim("email", email)
                .sign(algorithmHS);
    }

    public String getUserEmailFromToken(String token){
        // use this method to get the email of user from token
        // it will automatically send error response in case of failure
        if(token == null){
            throw new UnauthorizedException("Invalid Token");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.getJwtSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.getIssuer())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("email").asString();
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            throw new UnauthorizedException("Invalid Token");
        }
    }

    private String getJwtSecret() {
        // fallback to random string if jwt_secret is not set
        String jwtSecret = System.getenv("JWT_SECRET");
        if(jwtSecret == null || jwtSecret.length() == 0){
            jwtSecret = "askdjasjdlaskjdlasjdlasjdklasjdaslkfdajhsfgajsgdhasd";
        }
        return jwtSecret;
    }

    private String getIssuer() {
        // fallback issuer if Issuer is not set
        String issuer = System.getenv("ISSUER");
        if(issuer == null || issuer.length() == 0){
            issuer = "VR_PROPERTY";
        }
        return issuer;
    }
}
