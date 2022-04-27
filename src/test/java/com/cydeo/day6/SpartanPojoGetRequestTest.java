package com.cydeo.day6;

import com.cydeo.day6_pojo.Search;
import com.cydeo.day6_pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Spartan spartan15 = response.as(Spartan.class);//from Spartan class we created
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

    @DisplayName("GET one spartan from search endpoint and use POJO")
    @Test
    public void test2(){

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath(); //as we indicate jsonPath object at top

//get the second spartan from the content list and put inside the spartan object
        Spartan spartan = jsonPath.getObject("content[1]", Spartan.class);
   //we indicate path name here instead of 'as' to deserialize and get exact result
        //in comparison with 'as' the 'getObject' with path is more flexible
        System.out.println(spartan.getName());
        System.out.println(spartan);

    }

    @DisplayName("GET one spartan from search endpoint and use POJO")
    @Test
    public void test3() {

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response(); //as we indicate response object at top

//get the full content json and convert it to Search object
        Search searchResult = response.as(Search.class);//from Search class we created
        //we can also use jackson to get Search result:
        // Search search2 = response.jsonPath().getObject("", Search.class);

        //as Search class contains 2 variables - int and List<Spartan>, we can call
        //these variables with getters below

        System.out.println(searchResult.getTotalElement());
        System.out.println(searchResult.getContent().get(1).getName());
        //'get(1)' is for getting info about index 1 spartan from List<Spartan>
        //as List contains Spartan class we refer to each variable of this class

    }

    @DisplayName("GET /api/spartans/search and save as List<Spartan>")
    @Test
    public void test4(){

        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Spartan> content = jsonPath.getList("content", Spartan.class);
        //we can put our jsonPath in one list to see all 'content'/getList is on help

        System.out.println(content.get(1).getName());
    }


}
