package com.cydeo.day12;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MethodSourceParameterizedTest {

    //get information and data from below created method:

    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name){
   //although we have declared List in our method we wanna get one String'name' at a time
        System.out.println("name = " + name);

    }

    @ParameterizedTest
    @MethodSource("getExcelData")//from method below
    public void excelParamTest(Map<String,String> userInfo){
        System.out.println("userInfo.get(\"firstname\") = " + userInfo.get("firstname"));
        System.out.println("userInfo.get(\"lastname\") = " + userInfo.get("lastname"));
        //get row from Map with first and last names

    }

    public static List<Map<String,String>> getExcelData(){//chained to above execution
        ExcelUtil vytrackFile = new ExcelUtil
                ("src/test/resources/Vytracktestdata.xlsx","QA3-all");
        //called method from ExcelUtil with indication of path of our xlsx file
        //and exact sheet name inside this file
        List<Map<String, String>> dataList = vytrackFile.getDataList();
        //assign our data from excel file to the List of Map

        return  dataList;
    }


    public static List<String> getNames(){
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Other APIs
        //Excel file

        List<String> nameList =
                Arrays.asList("Muhtar","Asya","Gurhan","Adam","Akbar","Aysun","Zulfikar");
        return nameList;
    }



}
