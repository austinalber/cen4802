package com.cen4802.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/greet")
    public String greet(@RequestParam(defaultValue = "DevOps Student") String name) {
        return "Hello, " + name + "! Welcome to CEN4802 Web Application v1.0.";
    }
}