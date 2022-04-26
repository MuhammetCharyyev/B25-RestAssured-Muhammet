package com.cydeo.day6;

import com.cydeo.day6_pojo.Employee;
import com.cydeo.day6_pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class HrPojoGetRequestTests extends HrTestBase {

    @Test
    public void test1(){
        JsonPath jsonPath = get("/regions").then().statusCode(200).log().body().
                extract().jsonPath();


        //we want to store second region under items in Region class object
        Region region2 = jsonPath.getObject("items[1]", Region.class);

        System.out.println("region2.getRegion_id() = " + region2.getRegionId());
        System.out.println(region2.getRegionName());
        System.out.println(region2.getLinks().get(0).getHref());

    }

    @Test
    public void test2() {
        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Employee empl = jsonPath.getObject("items[0]", Employee.class);
        //if we need to get info about only some points of this employee

        System.out.println("empl.getJobId() = " + empl.getJobId());
        System.out.println(empl.getFirstName());
        System.out.println(empl.getLastName());
        System.out.println(empl.getSalary());


    }

     /*
        send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non-used fields
     */

    }
