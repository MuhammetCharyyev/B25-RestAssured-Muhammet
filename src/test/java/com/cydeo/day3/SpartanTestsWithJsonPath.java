package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithJsonPath extends SpartanTestBase {

    @DisplayName("GET one spartan with Json Path")
    @Test
    public void test1() {

        Response response = given()
                .log().all()//show in console what you requested, all steps
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)//we indicate our parameters key/value
                .when()
                .get("/api/spartans/{id}");//we put key parameter in curly braces

        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        System.out.println(response.path("name").toString());

        //print name/ save Json Path object from response
        JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.getString("name");
        System.out.println("name = " + name);

        //save into variables
        int id = jsonPath.getInt("id");
        String name1 = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        //assertions
        assertEquals(10,id);
    }

}
