package com.cydeo.day6;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class tasks extends HrTestBase {

/**
 * - Given accept type is Json
 * - Path param value- US
 * - When users sends request to /countries
 * - Then status code is 200
 * - And Content - Type is Json
 * - And country_id is US
 * - And Country_name is United States of America
 * - And Region_id is
 */

@Test
public void test1(){
    given()//here is request part is starting
            .accept(ContentType.JSON)
            .and()
            .pathParam("country_id", "US")
            .when()
            .get("http://18.215.145.203:1000/ords/hr/countries/{country_id}")
            .then()//'then' is for response part, means you are starting assertion here
            .statusCode(200)
            .and()
            .contentType("application/json")
            .and()
            .body("country_id",is("US"), //verify full body by id
                    "country_name",is("United States of America"),
                    "region_id",equalTo( 2));

}

/**
 * - Given accept type is Json
 * - Query param value - q={"department_id":80}
 * - When users sends request to /employees
 * - Then status code is 200
 * - And Content - Type is Json
 * - And all job_ids start with 'SA'
 * - And all department_ids are 80
 * - Count is 25
 */

@Test
public void test2() {

    JsonPath jsonPath = given()
            .accept(ContentType.JSON)
            .and()
            .queryParam("q","{\"department_id\":80}")
            .when()
            .get("/employees")
            .then()
            .statusCode(200)
            .and()
            .body("items.department_id", everyItem(equalTo(80)) )
            .body("items.job_id", everyItem(startsWith("SA")))
            .body("count",is (25))
            .extract().response().jsonPath();

    assertThat(jsonPath.getList("items.department_id"),hasSize(25));

}

    /**
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */
    @Test
    public void test3() {

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"region_id\":3}")
                .when()
                .get("/countries")
                .then()
                .statusCode(200)
                .and()
                .body("items.region_id", everyItem(equalTo(3)) )
                .body("count",is (6))
                .body("items.country_name", hasItems("Australia","China","India","Japan",
                        "Malaysia","Singapore" ))
                .extract().response().jsonPath();

        assertThat(jsonPath.getList("items.region_id"),hasSize(6));


    }



}
