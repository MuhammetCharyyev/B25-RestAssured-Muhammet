package com.cydeo.day7;

import com.cydeo.utilities.SpartanTestBase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanPostRequestDemo extends SpartanTestBase {

      /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @DisplayName("Post a spartan with String json body")
    @Test
    public void test1(){

        String requestBody  ="{\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"Severus\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";
        //we make String to include our json body
        Response response = given().accept(ContentType.JSON).log().all()
                // what we are asking from api which is JSON response
                .and()
                .contentType(ContentType.JSON)
                //what we are sending to api, which is JSON request body
                .body(requestBody)
                .when()
                .post("/api/spartans");//instead of 'get' we send 'post' or creat new

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        //verify if the message for key 'success' is matching

        assertThat(response.path("success"),is(expectedMessage));
        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(8877445596l));

        response.prettyPrint();

    }

}
