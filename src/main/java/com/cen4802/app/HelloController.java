package com.cen4802.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/api/greet")
    public String greet(@RequestParam(required = false) String name) {
        String greetingName = name == null ? "DevOps Student" : name;
        if (greetingName.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }

        log.debug("Greeting request accepted: customName={}, nameLength={}",
                name != null, greetingName.length());
        log.info("Greeting generated: customName={}, nameLength={}",
                name != null, greetingName.length());

        // Updated version string to v3.0 and added build verification text
        return "Hello, " + greetingName + "! Welcome to CEN4802 Web Application v2.1 (Rebuilt via Maven). " +
               "Server Time: " + LocalDateTime.now() + " | Status: Operational";
    }

    @GetMapping("/api/status")
    public Map<String, Object> getStatus() {
        log.info("Status requested: application=CEN4802 DevOps App, version=2.1.0");
        Map<String, Object> statusMap = new HashMap<>();
        statusMap.put("application", "CEN4802 DevOps App");
        statusMap.put("version", "2.1.0");
        statusMap.put("buildTool", "Apache Maven");
        statusMap.put("status", "Rebuilt Successfully");
        statusMap.put("timestamp", LocalDateTime.now().toString());
        return statusMap;
    }

    @GetMapping("/midterm")
    public String getMidtermStatus() {
        log.info("Midterm integration status requested");
        return "CEN 4802 Midterm Project - Successfully Integrated Pipeline";
    }
}