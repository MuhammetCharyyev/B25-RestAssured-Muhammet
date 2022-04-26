package com.cydeo.day6_pojo.ctraininig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    //we create separate class for Address because it is
// a separate json object in 'students->company'


    private String companyName;
    private Address address;
    //we create variable for Address class as it includes separate variables as well
}
