package com.cydeo.day6_pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data //from lombok dependency /replace all below annotations
//@Getter
//@Setter
//@ToString

public class Region {

@JsonProperty ("region_id")//JsonProperty will convert your variable name
// to Json object as name in Json is 'region_id'
    private int regionId;//it is better to name variable with camelCase
@JsonProperty ("region_name")//JsonProperty will convert your variable name
// to Json object as name in Json is 'region_name'
    private String regionName;
    private List<Link> links; // List of our class 'Link'

}
