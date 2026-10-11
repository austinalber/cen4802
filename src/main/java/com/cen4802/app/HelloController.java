package com.cen4802.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/api/greet")
    public String greet(@RequestParam(defaultValue = "DevOps Student") String name) {
        // Updated version string to v3.0 and added build verification text
        return "Hello, " + name + "! Welcome to CEN4802 Web Application v2.1 (Rebuilt via Maven). " +
               "Server Time: " + LocalDateTime.now() + " | Status: Operational";
    }

    // New Endpoint added specifically for Step 6 Rebuild Demonstration
    @GetMapping("/api/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> statusMap = new HashMap<>();
        statusMap.put("application", "CEN4802 DevOps App");
        statusMap.put("version", "2.1.0");
        statusMap.put("buildTool", "Apache Maven");
        statusMap.put("status", "Rebuilt Successfully");
        statusMap.put("timestamp", LocalDateTime.now().toString());
        return statusMap;
    }

    
}