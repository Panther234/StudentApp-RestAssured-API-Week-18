package com.studentapp.studentifo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentPatchTest extends TestBase {

    @Test
    public void updateStudentData() {

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setEmail("Bob1@googlemail.com"); //only email updated
        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 109) // add param id
                .body(studentPojo)
                .when()
                .patch("/{id}"); //using Patch
        response.then().statusCode(200); // statusCode 200
        response.prettyPrint();
    }
}
