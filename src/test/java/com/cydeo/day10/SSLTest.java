package com.cydeo.day10;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class SSLTest {

    @Test
    public void test1(){
        given().relaxedHTTPSValidation()
                //with adding you let the system know that you know about unsecure
                //you trust it and you anyway want to send request
                //this is useful when your API doest not have certificate
                .when()
                .get("https://untrusted-root.badssl.com/")
                //path of not secure URL
            //if just send request to unsecure URL it will give SSLHandshake exception
                .prettyPrint();
    }


    @Test
    public void test2(){

        given().keyStore("","")
                //you may provide with certificate to secure API
                // with indication it in quotes
                .when().get("url");

    }


}
