package com.cen4802.app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppApplicationTests {

	@Value("${local.server.port}")
	private int port;

	private final HttpClient httpClient = HttpClient.newHttpClient();

	@Test
	void contextLoads() {
	}

	@Test
	void blankGreetingIsReturnedAsBadRequest() throws Exception {
		HttpResponse<String> response = get("/api/greet?name=");

		assertEquals(400, response.statusCode());
		assertTrue(response.body().contains("\"error\":\"Bad Request\""));
		assertTrue(response.body().contains("\"path\":\"/api/greet\""));
	}

	@Test
	void normalEndpointsRemainAvailable() throws Exception {
		HttpResponse<String> greeting = get("/api/greet");
		HttpResponse<String> status = get("/api/status");
		HttpResponse<String> midterm = get("/midterm");

		assertEquals(200, greeting.statusCode());
		assertTrue(greeting.body().contains("Hello, DevOps Student!"));
		assertEquals(200, status.statusCode());
		assertTrue(status.body().contains("\"application\":\"CEN4802 DevOps App\""));
		assertEquals(200, midterm.statusCode());
		assertTrue(midterm.body().contains("Successfully Integrated Pipeline"));
	}

	@Test
	void repeatedGreetingRequestsExerciseNormalApplicationLogging()
			throws IOException, InterruptedException {
		for (int i = 1; i <= 110; i++) {
			HttpResponse<String> response = get("/api/greet?name=Student" + i);
			assertEquals(200, response.statusCode());
		}
	}

	private HttpResponse<String> get(String path) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:" + port + path))
				.GET()
				.build();
		return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	}
}
