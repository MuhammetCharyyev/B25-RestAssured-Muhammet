package com.cydeo.day6;

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
        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();


        //we want to store second region under items in Region class object
        Region region2 = jsonPath.getObject("items[1]", Region.class);

        System.out.println("region2.getRegion_id() = " + region2.getRegion_id());
        System.out.println(region2.getRegion_name());
        System.out.println(region2.getLinks().get(0).getHref());

    }

}
