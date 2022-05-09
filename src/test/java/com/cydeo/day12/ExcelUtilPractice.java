package com.cydeo.day12;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ExcelUtilPractice {

    @Test
    public void test1(){

        //how to use ExcelUtil ?
        //it accepts two arguments
        //Arg1: location of the file(path)
        //Arg2: worksheet that we want to open

        ExcelUtil vytrackFile = new ExcelUtil
                ("src/test/resources/Vytracktestdata.xlsx","QA3-short");
        //called method from ExcelUtil with indication of path of our xlsx file
        //and exact sheet name inside this file

        List<Map<String, String>> dataList = vytrackFile.getDataList();
        //assign our data from excel file to the List of Map

        for (Map<String, String> rowMap : dataList) {
            System.out.println(rowMap);
            //iterate whole List of Map to get each row
        }

    }
}
