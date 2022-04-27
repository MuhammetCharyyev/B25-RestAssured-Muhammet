package com.cydeo.day6_pojo.hr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data //from lombok dependency /replace all getters and setters

@JsonIgnoreProperties (ignoreUnknown = true)
//tell to Jackson to ignore unknown json keys if
// we dont have corresponding variables in class and in API, with underscore, for examp.
// as we indicate here only several variables

public class Employee {

    @JsonProperty("first_name")
    //we indicate JsonProperty to ignore underscore and tell Jackson to consider that
    // my variable name is the same as in API
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("job_id")
    private String jobId;
    private int salary;


}
