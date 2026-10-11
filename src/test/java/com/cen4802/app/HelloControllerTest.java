package com.cen4802.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    private HelloController controller;

    @BeforeEach
    void setUp() {
        controller = new HelloController();
    }

    @Test
    @DisplayName("Test /api/greet with default parameter")
    void testGreetDefaultName() {
        String result = controller.greet("DevOps Student");
        
        assertNotNull(result, "Response should not be null");
        assertTrue(result.contains("Hello, DevOps Student!"), "Response should include default name greeting");
        assertTrue(result.contains("v2.1"), "Response should reflect version 2.1");
        assertTrue(result.contains("Status: Operational"), "Response should indicate operational status");
    }

    @Test
    @DisplayName("Test /api/greet with custom parameter")
    void testGreetCustomName() {
        String result = controller.greet("Austin");
        
        assertNotNull(result, "Response should not be null");
        assertTrue(result.contains("Hello, Austin!"), "Response should address custom user 'Austin'");
    }

    @Test
    @DisplayName("Test /api/status payload structure and values")
    void testGetStatusMap() {
        Map<String, Object> status = controller.getStatus();

        assertNotNull(status, "Status payload should not be null");
        assertEquals("CEN4802 DevOps App", status.get("application"));
        assertEquals("2.1.0", status.get("version"));
        assertEquals("Apache Maven", status.get("buildTool"));
        assertEquals("Rebuilt Successfully", status.get("status"));
        assertTrue(status.containsKey("timestamp"), "Status payload should contain a timestamp key");
    }

@Test
    public void testMidtermEndpoint() {
        HelloController controller = new HelloController();
        String response = controller.getMidtermStatus();
        assertEquals("Incorrect string", response);
    }
}