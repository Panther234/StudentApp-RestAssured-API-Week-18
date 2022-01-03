package com.studentapp.studentifo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentCRUDTest extends TestBase {

    static String firstName = "Harry" + TestUtils.getRandomValue();
    static String lastName = "Patel" + TestUtils.getRandomValue();
    static String programme = "Pharmacy";
    static String email = "Harry@gmail.com" + TestUtils.getRandomValue();
    static int studentId;

    @Test
    //Add (POST) new student data
    public void test001() {
        List<String> courseList = new ArrayList<>();
        courseList.add("Anatomy");
        courseList.add("Medicine");

        StudentPojo studentPojo = new StudentPojo(); // create object of StudentPojo class
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    //Get Student id of new added student in Test001 and store in studentId variable
    @Test
    public void test002() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value =
                given()
                        .when()
                        .get("/list")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);
        System.out.println(value);
        studentId = (int) value.get("id");
    }

    //update first name and courses getting using studentId -- (PUT) method and verify that its updated
    @Test
    public void test003() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        //update FirstName
        firstName = firstName + "_Updated";

        //update course content
        List<String> courseList = new ArrayList<>();
        courseList.add("Generic Medicine");
        courseList.add("Human Body");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        given()
                .header("Content-Type", "application/json")
                .pathParam("studentID", studentId)
                .body(studentPojo)
                .when()
                .put("/{studentID}")
                .then().log().all().statusCode(200);

        //verify that response through id with after  extract by FirstName
        HashMap<String, Object> value =

                given()
                        .when()
                        .get("/list")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);
        System.out.println(value);
    }

    //delete student record and verify that record deleted successfully
    @Test
    public void test004() {

        given()
                .pathParam("studentID", studentId)
                .when()
                .delete("/{studentID}")
                .then()
                .statusCode(204);

        given()
                .pathParam("studentID", studentId)
                .when()
                .get("/{studentID}")
                .then()
                .statusCode(404);
    }
}
