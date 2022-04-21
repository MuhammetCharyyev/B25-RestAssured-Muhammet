package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestBase {
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://18.215.145.203:8000";
        //added in Before all to keep this before all tests
    }
}
