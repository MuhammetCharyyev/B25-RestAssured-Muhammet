package com.cydeo.day6_pojo.ctraininig;

import lombok.Data;

import java.util.List;

@Data
//as it is an root directory for all others we dont need  @JsonIgnoreProperties here
public class Students {

    private List<Student> students;//include list of info from Student class

}
