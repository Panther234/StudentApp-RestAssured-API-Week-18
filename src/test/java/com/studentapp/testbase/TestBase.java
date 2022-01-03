package com.studentapp.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {


    //    http://localhost:8080/student/list
    //          baseURI   |port|basePath|

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/student";
    }
}
