package com.cydeo.day6_pojo;

import java.util.List;
import lombok.*;

@Getter //from lombok dependency
@Setter
@ToString

public class Region {


    private int region_id;
    private String region_name;
    private List<Link> links; // Lis of our class 'Link'

}
