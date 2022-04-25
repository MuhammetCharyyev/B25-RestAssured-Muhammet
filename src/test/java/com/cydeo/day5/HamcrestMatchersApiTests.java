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
                .body("id",is(15), //verify full body by id
                        "name",is("Meta"),
                        "gender",equalTo("Female"),
                        "phone",is(1938695106));

    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void test2(){
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",10423)
                .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Encoding","gzip")//verification header content
                .and()
                .header("Date",notNullValue())
                //as date is dynamic data then we may verify only if header is not null
                .body("teachers[0].firstName",is("Alexander"),
                        "teachers[0].lastName",is("Syrup"),
                        "teachers[0].gender",equalTo("male"));
        //we put index [0] because 'teacher' has this body inside by this index#

    }

    @Test
    public void test3(){
        //verify "Candi","Alexander","Francesca" inside the all teachers
        given()
                .accept(ContentType.JSON)
                .when()
                .get("http://api.cybertektraining.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Candi","Alexander","Francesca"));
        //check all 'teacher' first names by consisting specific names (hasItems)
    }



}
