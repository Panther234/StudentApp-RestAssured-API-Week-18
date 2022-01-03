package com.studentapp.studentifo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPostTest extends TestBase {

    @Test
    public void createNewStudent() {
        List<String> courseList = new ArrayList<>();
        courseList.add("Mechanical");
        courseList.add("Civil");
        courseList.add("Chemical");

        StudentPojo studentPojo = new StudentPojo(); // create object of StudentPojo class
        studentPojo.setFirstName("Bob");
        studentPojo.setLastName("Alexa");
        studentPojo.setEmail("Bob1@gmail.com");
        studentPojo.setProgramme("Engineering");
        studentPojo.setCourses(courseList);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }
}
