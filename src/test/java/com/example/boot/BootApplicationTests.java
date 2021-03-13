package com.example.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BootApplicationTests {

    private static final int DEV_PORT = 8080;
    private static final int PROD_PORT = 8081;
    private static final String HOST = "http://localhost:";
    @Container
    private static final GenericContainer<?> DEV_APP = new GenericContainer<>("devapp:latest")
            .withExposedPorts(DEV_PORT);
    @Container
    private static final GenericContainer<?> PROD_APP = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(PROD_PORT);
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test_getProfile_showsDevProfileMessage() {
        final Integer mappedPort = DEV_APP.getMappedPort(DEV_PORT);
        final ResponseEntity<String> forEntity = restTemplate.getForEntity(HOST + mappedPort + "/profile", String.class);
        final String expected = "Current profile is dev";
        assertEquals(expected, forEntity.getBody());
    }

    @Test
    void test_getProfile_showsProductionProfileMessage() {
        final Integer mappedPort = PROD_APP.getMappedPort(PROD_PORT);
        final ResponseEntity<String> forEntity = restTemplate.getForEntity(HOST + mappedPort + "/profile", String.class);
        final String expected = "Current profile is production";
        assertEquals(expected, forEntity.getBody());
    }
}
