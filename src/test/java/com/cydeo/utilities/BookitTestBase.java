package com.cydeo.utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class BookitTestBase {

    public static RequestSpecification teacherReqSpec;//make it global for teacher
    public static ResponseSpecification responseSpec;//make it global
    public static RequestSpecification studentReqSpec;//make it global for student

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("base_url");

        teacherReqSpec = given()//insert token with credentials
                .header("Authorization",getTokenByRole("teacher"))
                .accept(ContentType.JSON)
                .log().all();

        studentReqSpec = given()
                .header("Authorization",getTokenByRole("student-member"))
                .accept(ContentType.JSON)
                .log().all();

        responseSpec = expect().statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);
    }

    //can we create a method that is returning RequestSpec based on the role we provided ?
    public static RequestSpecification userReqSpec(String role){

        return given()
                .header("Authorization",getTokenByRole(role))
                .accept(ContentType.JSON)
                .log().all();

    }

    public static ResponseSpecification dynamicResponseSpec(int statusCode){
        return expect().statusCode(statusCode)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);

    }


    //create a method that returns Bearer+token based on provided role
    //teacher,student-member,student-leader
    //it will take info from configuration properties
    public static String getTokenByRole(String role) {
        String email = "";
        String password = "";

        switch (role) {
            case "teacher":
                email = ConfigurationReader.getProperty("teacher_email");
                password = ConfigurationReader.getProperty("teacher_password");
                break;
            case "student-member":
                email = ConfigurationReader.getProperty("team_member_email");
                password = ConfigurationReader.getProperty("team_member_password");
                break;
            case "student-leader":
                email = ConfigurationReader.getProperty("team_leader_email");
                password = ConfigurationReader.getProperty("team_leader_password");
                break;
            default:

                throw new RuntimeException("Invalid Role Entry :\n>> " + role +" <<");
        }

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        String path = given()
                .queryParams(credentials)
                .when().get( "/sign").path("accessToken");

        return  "Bearer " + path;

    }

    @AfterAll
    public static void close(){
        reset();
    }

}
