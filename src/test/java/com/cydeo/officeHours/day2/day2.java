package com.cydeo.officeHours.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class day2 {

    /**
     *
     *
     *
     *
     *
     *
     */


    //   1. Send request to get all Spartans  and get me the status code
   //2. get me headers for above request
    // 3. get me header value for Content-Type and Date
    // 4. get me all Spartans printed
    String url_spartans = "http://44.201.135.133:8000/api/spartans";

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(url_spartans);
//        System.out.println(response.getStatusCode());
//        System.out.println(response.getHeaders());
//        System.out.println(response.headers());
//        System.out.println(response.getHeader("Content-Type"));
//        System.out.println(response.getHeader("Date"));
        response.prettyPrint();
        response.body().prettyPrint();

    }


  //5. get me 7th spartan (2 different ways)
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(url_spartans + "/7");
        response.prettyPrint();

        Response response1 = RestAssured.given().accept(ContentType.JSON)
                .when().pathParams("id", 7)
                .when().get(url_spartans + "/{id}");
        response1.prettyPrint();

    }


    //6. get me Spartans name Allen
    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().queryParam("nameContains", "Allen")
                .when().get(url_spartans + "/search");

        response.prettyPrint();
    }


    // 7. get me all male Spartans
    @Test
    public void test4(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().queryParam("gender", "Male")
                .when().get(url_spartans + "/search");
        response.prettyPrint();
    }

    // 8. get me the spartan that female and name has z
    @Test
    public void test5(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().queryParam("gender", "Female")
                .when().queryParam("nameContains","z")
                .when().get(url_spartans + "/search");

        response.prettyPrint();
    }

    //9. get name, gender, phone separately of No.15 Spartans
    @Test
    public void test6(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().pathParams("id", 15)
                .when().get(url_spartans + "/{id}");

        // first way
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        // second way using jsonpath

        JsonPath jsonPath = response.jsonPath();
        byte id = jsonPath.getByte("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        Long phone = jsonPath.getLong("phone");

        System.out.println(id);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);

    }

    //10. get me all Spartans and get 10st Spartan's name, 20st Spartans gender,
    // 30st Spartans id.
    @Test
    public void test7(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(url_spartans);

        String name10 = response.path("name[9]");
        String gender20 = response.path("gender[19]");
        int id30 = response.path("id[29]");

        System.out.println(name10);
        System.out.println(gender20);
        System.out.println(id30);
    }


}
