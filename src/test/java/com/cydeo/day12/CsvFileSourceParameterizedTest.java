package com.cydeo.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CsvFileSourceParameterizedTest {

    //get information/data from csv file
    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}

    @ParameterizedTest
    @CsvFileSource( resources = "/zipcode.csv", numLinesToSkip = 1)
    //you indicate resources file from where you'll get info
    //numLinesToSkip helps to skip unnecessary lines from data
    public void zipCodeTest(String state,String city, int zipCount) {

        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("zipCount = " + zipCount);

        given()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state",state)
                .and()
                .pathParam("city",city)
                .when().get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .and()
                .body("places", hasSize(zipCount));
        //hasSize will get you count of zipCount

    }


    }
