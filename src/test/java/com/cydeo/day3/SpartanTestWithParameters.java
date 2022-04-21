package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithParameters extends SpartanTestBase {

/*   Given accept type is Json
     And Id parameter value is 5
     When user sends GET request to /api/spartans/{id}
     Then response status code should be 200
      And response content-type: application/json
      And "Blythe" should be in response payload
 */

    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1(){

        Response response = given()//we remove 'RestAssured' as it is '*' in class signature
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",5)//we indicate our parameters key/value
                .when()
                .get("/api/spartans/{id}");//we put key parameter in curly braces

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json",response.contentType());

        //verify "Blythe" inside the payload
        assertTrue(response.body().asString().contains("Blythe"));

    }

     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET request to /api/spartans/{id} with ID 500")
    @Test
    public void test2(){

        Response response = given()//we remove 'RestAssured' as it is '*' in class signature
                .accept(ContentType.JSON)
                .and()//it is not necessary just for better reading
                .pathParam("id",500)//we indicate our parameters key/value
                .when()
                .get("/api/spartans/{id}");//we put key parameter in curly braces

        //verify status code
        assertEquals(404,response.statusCode());

        //verify content type
        assertEquals("application/json",response.header("Content-Type"));

        //verify "Not Found" inside the payload
        assertTrue(response.body().asString().contains("Not Found"));

    }

      /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3(){
        //Map using option:

        /*
          Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().log().all().
                             accept(ContentType.JSON).
                             queryParams(queryMap)
                .when().
                        get("/api/spartans/search");
         */

        Response response = given()//we remove 'RestAssured' as it is '*' in class signature
                .log().all()//show in console what you requested, all steps
                .accept(ContentType.JSON)
                .and()
                .queryParam("gender","female")//we indicate our parameters key/value
                .and()
                .queryParam("nameContains","e")
                .when()
                .get("/api/spartans/search");//we indicate path for search

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json",response.contentType());

        //verify "Female" inside the payload
        assertTrue(response.body().asString().contains("Female"));

        //verify "Janette" inside the payload
        assertTrue(response.body().asString().contains("Janette"));

    }

}
