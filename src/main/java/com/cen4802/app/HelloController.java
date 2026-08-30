package com.cen4802.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
public class HelloController {

    @GetMapping("/api/greet")
    public String greet(@RequestParam(defaultValue = "DevOps Student") String name) {
        return "Hello, " + name + "! Welcome to CEN4802 Web Application v2.0. " +
               "Server Time: " + LocalDateTime.now() + " | Status: Operational";
    }
}