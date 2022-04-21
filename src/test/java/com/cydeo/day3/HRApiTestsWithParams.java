package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiTestsWithParams extends HrTestBase {

    @Test
    public void test1() {
        Response response = get("/regions");

        response.prettyPrint();

    }

    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /api/hr/countries with Query Params")
    @Test
    public void test2() {

        Response response = given()
                .log().all()//show in console what you requested, all steps/optional
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"region_id\":2}")
                .when()
                .get("/countries");//we indicate path for search

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json",response.contentType());

        //verify USA exists
        assertTrue(response.body().asString().contains("United States of America"));
    }

    /*
        HW
        Send a GET request to employees and get only employees who works as a IT_PROG

  */

}
