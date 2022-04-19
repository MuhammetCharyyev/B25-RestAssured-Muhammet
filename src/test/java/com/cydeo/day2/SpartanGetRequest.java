package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
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

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when().get(url + "/api/spartans");
        //make request to spartns API with acceptance Json

        System.out.println("statusCode = " + response.statusCode());//print status code/200

        System.out.println("contentType = " + response.contentType());//application/json

        //how to test API ?
        //verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type is application json
        Assertions.assertEquals("application/json", response.contentType());
    }

         /*
        Given accept header is application/json
        When users send a get request to /api/spartans/3
        Then status code must be 200
        And Content type must be application/json
        And json body should contain 'Fidole'
     */

        @Test
        public void test2() {

            Response response=  RestAssured.given()
                    .accept(ContentType.JSON)
                    .when().get(url+"/api/spartans/3");
            //make request to spartans API with acceptance Json

            System.out.println("statusCode = " + response.statusCode());//print status code/200

            System.out.println("contentType = " + response.contentType());//application/json

            System.out.println("contains(\"Fidole\") = " + response.body().asString().contains("Fidole"));

            //how to test API ?
            //verify status code is 200
            Assertions.assertEquals(200, response.statusCode());

            //verify content type is application json
            Assertions.assertEquals("application/json",response.contentType());

            //verify 'Fidole' exists in Json body for spartan 3
            Assertions.assertTrue(response.body().asString().contains("Fidole"));
        }

         /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */
/*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */
         @DisplayName("GET request to /api/hello endpoint")
         @Test
         public void test3(){

             Response response = RestAssured.when().get(url + "/api/hello");

             response.prettyPrint();

             //verify status code
             Assertions.assertEquals(200,response.statusCode());

             //verify content type
             Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

             //verify Date header exists in Response headers
             //we use hasHeaderWithName method to verify header exists or not - it returns boolean
             Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

             //to get header value we use header() method which accepts header name as parameter and return value as string
             System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
             System.out.println("response.header(\"Connection\") = " + response.header("Connection"));

             //verify content length is 17
             Assertions.assertEquals("17",response.header("Content-Length"));

             //verify body is "hello from sparta"
             Assertions.assertEquals("Hello from Sparta",response.body().asString());

         }




}
