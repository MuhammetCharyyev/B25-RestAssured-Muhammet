package com.cydeo.day6_pojo.ctraininig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)

public class Address {
//we create separate class for Address because it is
// a separate json object in 'students->company->address'

    private String street;
    private int zipCode;
    private String state;

}
