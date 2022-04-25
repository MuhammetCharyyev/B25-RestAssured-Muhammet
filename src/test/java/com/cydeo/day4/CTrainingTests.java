package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CTrainingTests {

    //beforeAll is the same thing with beforeClass in testng
    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://api.cybertektraining.com";
    }

    //send a get request to student id 24103 as a path parameter and
    // accept header application/json
    //verify status code=200
    // /content type=application/json;charset=UTF-8
    // /Content-Encoding = gzip
    //verify Date header exists
    //assert that
            /*
                firstName Karole
                batch 7
                major Master of Creative Arts
                emailAddress hassan.lebsack@hotmail.com
                companyName Legacy Integration Analyst
                street 6253 Willard Place
                zipCode 28524

                using JsonPath
             */

    @Test
    public void test1() {
        //send a get request to student id 24103 as a path parameter and
        Response response = given()
                // accept header application/json
                .accept(ContentType.JSON)
                .pathParam("id", 24103)
                .when()
                .get("/student/{id}");
//verify status code=200
        assertEquals(200, response.statusCode());

        //content type=application/json;charset=UTF-8
        assertEquals("application/json;charset=UTF-8", response.contentType());

        //how to get any header
        assertEquals("gzip", response.header("Content-Encoding"));

        //verify Date header exists
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //payload verification:
        //we want to use JsonPath to get value
        JsonPath jsonPath = response.jsonPath();

        //we need to get from package 'students' just first record, that is why index [0]
        //if we dont see [] it means that there is no array and no index numbers
        assertEquals("Karole", jsonPath.getString("students[0].firstName"));
        assertEquals(7,jsonPath.getInt("students[0].batch"));
        assertEquals("Master of Creative Arts",jsonPath.getString("students[0].major"));
        assertEquals("hassan.lebsack@hotmail.com",jsonPath.getString("students[0].contact.emailAddress"));
        assertEquals("Legacy Integration Analyst",jsonPath.getString("students[0].company.companyName"));
        assertEquals("6253 Willard Place",jsonPath.getString("students[0].company.address.street"));
        assertEquals(28524, jsonPath.getInt("students[0].company.address.zipCode"));
        //console in Postman will show if result is int or String

    }


}
