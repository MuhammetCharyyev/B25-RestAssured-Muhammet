package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTests {

    /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("One Spartan with Hamcrest and Chaining")
    @Test
    public void test1(){
        given()//here is request part is starting
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",15)
                .when()
                .get("http://44.201.121.105:8000/api/spartans/{id}")
                .then()//'then' is for response part, means you are starting assertion here
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id",is(15),
                        "name",is("Meta"),
                        "gender",equalTo("Female"),
                        "phone",is(1938695106));

    }


}
