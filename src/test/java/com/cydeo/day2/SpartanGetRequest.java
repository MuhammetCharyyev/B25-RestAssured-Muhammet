package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String url = "http://18.215.145.203:8000";

    /*
        Given Accept type is application/json
        When user send GET request to  /api/spartans end point
        Then status code must be 200
        And response content type must be application/json
     */

    @Test
    public void test1() {

       Response response=  RestAssured.given()
                                   .accept(ContentType.JSON)
                                   .when().get(url+"/api/spartans");
       //make request to spartns API with acceptance Json

        System.out.println("statusCode = " + response.statusCode());//print status code/200

        System.out.println("contentType = " + response.contentType());//application/json

        //how to test API ?
        //verify status code is 200
      Assertions.assertEquals(200, response.statusCode());

        //verify content type is application json
        Assertions.assertEquals("application/json",response.contentType());


    }
}
