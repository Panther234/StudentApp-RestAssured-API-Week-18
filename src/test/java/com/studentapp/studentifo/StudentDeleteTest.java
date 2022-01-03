package com.studentapp.studentifo;

import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {

    @Test
    public void deleteStudentData() {

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 121) // add student id
                .when()
                .delete("/{id}"); //using Delete
        response.then().statusCode(204); // statusCode 204
        response.prettyPrint();
    }
}
