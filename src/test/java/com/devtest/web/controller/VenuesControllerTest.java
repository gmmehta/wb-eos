package com.devtest.web.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class VenuesControllerTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void whenPlaceIsUnknownThenReturnNoVenuesFound() throws Exception {
        when().get("/api/venues/nearby/abcunknownabc")
                .then()
                .statusCode(SC_OK)
                .body("data.venues", Matchers.hasItems("No venues found"));
    }

    @Test
    public void whenPlaceIsKnownThenReturnListWithVenues() throws Exception {
        when().get("/api/venues/nearby/holborn")
                .then()
                .statusCode(SC_OK)
                .body("data.venues", Matchers.hasItems("National Gallery", "British Museum", "Somerset House", "Dishoom"));
    }
}
