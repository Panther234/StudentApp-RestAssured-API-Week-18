package com.studentapp.studentifo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPutTest extends TestBase {

    @Test
    public void updateStudentDataWithEmail() {
        List<String> courseList = new ArrayList<>();
        courseList.add("Mechanical");
        courseList.add("Civil");
        courseList.add("Chemical");

        StudentPojo studentPojo = new StudentPojo(); // create object of StudentPojo class
        studentPojo.setFirstName("Bob");
        studentPojo.setLastName("Alexa");
        studentPojo.setEmail("Bob1@yahoo.com");//email updated
        studentPojo.setProgramme("Engineering");
        studentPojo.setCourses(courseList);

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 109) // add param id
                .body(studentPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
