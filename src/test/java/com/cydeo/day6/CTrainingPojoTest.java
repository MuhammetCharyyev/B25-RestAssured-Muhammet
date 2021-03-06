package com.cydeo.day6;

import com.cydeo.day6_pojo.ctraininig.Student;
import com.cydeo.day6_pojo.ctraininig.Students;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CTrainingPojoTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="http://api.cybertektraining.com";
    }

    //send a get request to student id 24103 as a path parameter and accept header application/json
    //verify status code=200
    // /content type=application/json;charset=UTF-8
    // /Content-Encoding = gzip
    //verify Date header exists
    //assert that
            /*
                firstName Karole
                batch 7
                major Master of Creative Arts
                emailAddress hassan.lebsack@hotmail.com
                companyName Legacy Integration Analyst
                street 6253 Willard Place
                zipCode 28524
                using JsonPath
             */

    @Test
    public void test1(){

        Response response= given().
                accept(ContentType.JSON)
                .and()
                .pathParam("abc",24103)
                .when()
                .get("/student/{abc}")
        .then()
                .statusCode(200)
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Date", notNullValue())
                .extract().response();
        //extract helps you to store your json path inside to Json or Response object to
        //allow you to implement deserialization, save response inside Collections
        //and also save response inside custom classes


        //payload/body verification
        /*
        firstName Karole                          --> students[0].firstName
        batch 7                                   --> students[0].batch
        major Master of Creative Arts             --> students[0].major
        emailAddress hassan.lebsack@hotmail.com   --> students[0].contact.emailAddress
        companyName Legacy Integration Analyst    --> students[0].company.companyName
        street 6253 Willard Place                 --> students[0].company.address.street
        zipCode 28524                             --> students[0].company.address.zipCode
         */

        //get jsonpath object
        Students students = response.as(Students.class);//deserialization here
//call from Students class
        System.out.println(students);
       Student student1 = students.getStudents().get(0);
       //get info about 1 student as below, put it in Students object

        JsonPath jsonPath = response.jsonPath();
        //convert Students to Json response

        assertEquals("Karole",student1.getFirstName());
        assertEquals(7,student1.getBatch());
        assertEquals("Master of Creative Arts",student1.getMajor());
        assertEquals("hassan.lebsack@hotmail.com",student1.getContact().getEmailAddress());
        assertEquals("Legacy Integration Analyst",student1.getCompany().getCompanyName());
        assertEquals("6253 Willard Place",student1.getCompany().getAddress().getStreet());
        assertEquals(28524,student1.getCompany().getAddress().getZipCode());

    }

}
