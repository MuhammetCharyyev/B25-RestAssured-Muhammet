package com.cydeo.day6;

import com.cydeo.day6_pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("Get one spartan and convert it ot Spartan object")
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).log().all()
                //put log().all() at response you'll see response console as pretty print
                //or you can put log().body() to see just body
                .extract().response();

        //De serialize --> JSON to POJO(Java custom class)
        //2 different way to do this
        //1.using as() method
        //we convert json response to spartan object with the help of Jackson
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);

        System.out.println(spartan15.getName());
        System.out.println(spartan15.getPhone());

        //second way of deserialize to java
        //2.using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("",Spartan.class);
        //we can put path from Postman to see exact spartan or all spartans
        // it is like 'as' to deserialize but more powerfull
        //you cannot convert directly if you use 'as'
        System.out.println(s15.getName());
        System.out.println(s15.getGender());

    }



}
