package com.cydeo.officeHours.day1;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class T3_SpartanAPI_BeforeAll_AfterAll {

    /**
     * Task 3:
     * 1. Send request to Spartan url and save the response
     * 2. GET /spartans
     * 3. Store the response in Response Object that comes from get Request
     * 4. Print out followings
     *      - response
     *      - Content-Type
     *      - Status Code
     *      - Get me third spartan
     *      - Get me third spartan gender
     *      - Get me third spartan name
     *      - Get me all spartan name
     */
//create BeforeAll method
    @BeforeAll
    public static void init() {
        baseURI = "http://3.86.82.161:8000";
        basePath = "/api";

    }

    @AfterAll
    public static void resetApi() {
        reset();
    }
    @Test
    public void test() {

        //1. Send request to Spartan url and save the response
        //2. GET /spartans
        //3. Store the response in Response Object that comes from get Request
        //4. Print out followings
        //     - response
        //     - Content-Type
        //     - Status Code
        //     - Get me third spartan
        //     - Get me third spartan gender
        //     - Get me third spartan name
        //     - Get me all spartan name


    }

}
