package com.cydeo.day11;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CsvSourceParameterizedTest {

    //Test first number + second number = third number
    //1,3,4
    //2,3,5
    //8,7,15
    //10,9,19
   // CSV - comma-separated values

    @ParameterizedTest
    @CsvSource({"1, 3 , 4",
                "2, 3 , 5",
                "8, 7 , 15",
                "10, 9 , 19"})
    public void testAddition(int num1,int num2,int sum){
//CSV can take parameters are separated by Java comma and use as table
        //but all parameters inside quotes will be considered as one parameter
        // to put into 'path param'
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);

        MatcherAssert.assertThat(num1+num2, Matchers.equalTo(sum));
        //Matchers come from Hamcrest
    }

// Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({"NY, New York",
               "CO, Denver",
                "VA, Fairfax",
                "VA, Arlington",
                "MA, Boston",
                "NY, New York",
                "MD, Annapolis"})
    public void testStateCity(String state, String city){
        Response response = given()
                .baseUri("https://api.zippopotam.us")
               .pathParam("state",state)
                .and()
               .pathParam("city", city)
                .log().all()
                .when()
                .get("/us/{state}/{city}")
                .then().log().all()
                .statusCode(200)
                .extract().response();

        MatcherAssert.assertThat(response.path("places.'place name'"),
                                      everyItem(containsString(city)));

    }


}
