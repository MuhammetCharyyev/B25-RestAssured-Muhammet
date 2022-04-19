package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleGetRequest {

    String url = "http://18.215.145.203:8000/api/spartans";

    @Test
    public void test1() {
        //send a get request and save response inside the Response object
       Response response =  RestAssured.get(url);

        System.out.println(response.statusCode());//print response status/ 200

        //print response body
        response.prettyPrint();

        assertEquals(200, response.statusCode());

    }
}