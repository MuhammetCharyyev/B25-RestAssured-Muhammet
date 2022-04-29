package com.cydeo.day7;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class SpartanWithAuthTest extends SpartanAuthTestBase {

    //RBAC (Role Base Access Control)

    @DisplayName("GET /api/spartans as a public user(guest) expect 401")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then().statusCode(401)
                //we sent request to admin part as unauthorised admin
                //and want to receive negative testing result
         //providing no username and no password will get you 401, unauthorised user.
                .log().all()
                .body("error",is("Unauthorized"));
    }

    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
        //how to provide 'admin admin' as a username and password ?

        given()
                .auth().basic("admin","admin")
                //we simulate insertion to Authorization our username and password
                .and().accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/spartans")
                .then().statusCode(200).log().all();
    }


// IF you log in as a user and try to put,patch, delete then you will get 403 - forbidden

    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditor(){
        given()
                .auth().basic("editor","editor")
                //if you want to do some changes info but you dont have rights to do it
                //then system will give you 403
                .and().accept(ContentType.JSON)
                .and().pathParam("id",40)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(403).log().all();

    }

    //IF you log in as an admin and try to put, patch,
    // therefore authorization is passed you will get 200 - OK


     /*
        As a homework,write a detailed test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */


}
