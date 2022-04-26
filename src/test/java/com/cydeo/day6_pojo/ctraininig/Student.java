package com.cydeo.day6_pojo.ctraininig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private String firstName;
    private int batch;
    private String major;
    private Contact contact;
    //we creat variable for Contact class as it includes separate variables as well
    private Company company;
    //we create variable for Company class as it includes separate variables as well
}