package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ResponseTimeTest extends SpartanAuthTestBase {

    @DisplayName("GET request to all spartans and verify response time")
    @Test
    public void test1(){

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .and()
                .time(both(greaterThan(500L)).and(lessThan(1200L)))
                //.time gives us to indicate range in millisec to get response
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());
        //getTime is getting info about response time in millisec


    }
}