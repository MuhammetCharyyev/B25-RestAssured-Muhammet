package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.*;

public class SpartanTestBase {
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://18.215.145.203:8000";
        //added in Before all to keep this before all tests

        String url_sp = "jdbc:oracle:thin:@18.215.145.203:1521:XE";
        String username_sp = "SP";
        String password_sp = "SP";

        DB_Utils.createConnection(url_sp,username_sp,password_sp);
    }

    @AfterAll
    public static void close(){
        DB_Utils.destroy();
    }
}
