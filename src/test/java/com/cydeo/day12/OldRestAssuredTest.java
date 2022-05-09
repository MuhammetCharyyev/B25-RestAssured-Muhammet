package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class OldRestAssuredTest extends SpartanTestBase {

    @Test
    public void getAllSpartan(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("id[0]",is(10))
                .body("id[1]",is(150))
                .log().all();
    }

    /*
        in previous version of RestAssured, the given when then style
        was actually written in given expect when format.
        it will assert all the result and give one answer and does not fail while thing
        if first one fail unlike new structure.
     */
    @Test
    public void getSpartanOldWay(){
        given() //request body
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
       .expect()//what we got as expectation
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("id[0]",is(10))
                //by index number
                .body("id[1]",is(150))
                .logDetail(LogDetail.ALL)
                .when()
                .get("/spartans");

    }

}
