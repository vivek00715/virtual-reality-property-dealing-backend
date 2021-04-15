package com.hashedin.virtualproperty.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    String test() {
        return "Hello from server";
    }
}
