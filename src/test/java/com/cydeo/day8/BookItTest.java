package com.cydeo.day8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class BookItTest {

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTMwOSIsImF1ZCI6InN0dWRlbnQtdGVhbS1tZW1iZXIifQ.Lkjp9IzmVSdznbpzxoPoilDDf3obGgnT2C4kkg01ZUI";

    @DisplayName("Get /api/campuses")
    @Test
    public void test1(){
//how to pass bearer token for BookIt
        given().header("Authorization", accessToken).accept(ContentType.JSON)
                .when().get("/api/campuses")
        .then().statusCode(200).log().all();



    }

}
