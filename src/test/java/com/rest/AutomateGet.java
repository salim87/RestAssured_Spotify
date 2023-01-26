package com.rest;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

public class AutomateGet {


    @Test
    public void status_code(){
        given().baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-629ae4dd5234241efc8f844d-f3cd54d439233d2e4fbcd835bf52de1ea2").
                when().get("/workspaces").
                then()
               // .log().all()
                .statusCode(200);
    }

    @Test
    public void response_body(){
        File file=new File("src/main/resources/createWorkspace.json");
        given().baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-62f05396794ed072ff8e90e6-37ab6f8f04424f37dd2de0a3993b0afe4d")
                .body(file).contentType(ContentType.JSON).when()
                .post("/workspaces").
                then()
                .log().all()
                .statusCode(200).
                body("workspace.name", equalTo("mySecondWorkspace"));
    }
}
