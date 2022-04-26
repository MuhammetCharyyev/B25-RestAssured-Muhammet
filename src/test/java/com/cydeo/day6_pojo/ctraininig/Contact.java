package com.cydeo.day6_pojo.ctraininig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Contact {

private String emailAddress;

}
