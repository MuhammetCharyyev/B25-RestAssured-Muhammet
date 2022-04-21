package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath extends SpartanTestBase {

    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @DisplayName("GET one spartan with Path")
    @Test
    public void test1() {

        Response response = given()//we remove 'RestAssured' as it is '*' in class signature
                .log().all()//show in console what you requested, all steps
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)//we indicate our parameters key/value
                .when()
                .get("/api/spartans/{id}");//we put key parameter in curly braces

        //verify status code
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //verify "Female" inside the payload
        assertTrue(response.body().asString().contains("Female"));

        //verify each json key has specific value
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        //save value into variables
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        //verify each value/ you can also use assert(expected, response.path("id")
        // instead of above statement
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);
    }

    //how to get info using index num
    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2() {

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans");

        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);

        String name = response.path("name[1]");
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-1]");
        System.out.println(lastFirstName);

        //how to get all names and store inside the list
        List<String> names = response.path("name");
        System.out.println(names);
        //print names one by one
        for (String s : names) {
            System.out.println(s);
        }

    }
}
