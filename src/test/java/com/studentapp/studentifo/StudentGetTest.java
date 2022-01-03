package com.studentapp.studentifo;

import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentInfo() {
        Response response = given()
                .when()
                .get("/list"); // get list of all students
        response.then().statusCode(200); //to validate statusCode
        response.prettyPrint(); //to print response into console
    }

    @Test
    public void getSingleStudentInfo() {
        Response response = given()
                .pathParam("id", 3)// using pathParameter to create variable
                .when()
                .get("{id}"); // get data of single student by id
        response.then().statusCode(200);//to validate statusCode
        response.prettyPrint();//to print response in console
    }

    @Test
    public void searchStudentWithParameter() {
        Response response = given()
                .queryParam("programme", "Computer Science")// using query to create variable
                .queryParam("limit", 5)// using query to create variable
                .when()
                .get("/list"); // get all student data
        response.then().statusCode(200);//to validate statusCode
        response.prettyPrint();//to print response in console
    }
    @Test
    public void searchStudentWithCourses(){
        Response response = given()
                .queryParam("courses", "Accounting")// using query to create variable
                .queryParam("limit", 2)// using query to create variable
                .when()
                .get("/list"); // get all student data
        response.then().statusCode(200);//to validate statusCode
        response.prettyPrint();//to print response in console
    }

}