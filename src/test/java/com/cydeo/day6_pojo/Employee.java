package com.cydeo.day6_pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data //from lombok dependency /replace all getters and setters

@JsonIgnoreProperties (ignoreUnknown = true)
//tell to Jakson to ignore unknown json keys we dont have corresponding variables in class

public class Employee {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("job_id")
    private String jobId;
    private int salary;


}
