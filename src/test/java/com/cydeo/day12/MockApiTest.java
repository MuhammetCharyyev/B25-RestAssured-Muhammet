package com.cydeo.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MockApiTest {

 //   https://2e1be392-cd51-4206-a7c2-3e870ea23408.mock.pstmn.io
    //link we created in Postman

    @Test
    public void test() {

        given()
                .baseUri("https://2e1be392-cd51-4206-a7c2-3e870ea23408.mock.pstmn.io")
                .and()
                .accept(ContentType.JSON)
                .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("firstName", is("John"));

    }


}
