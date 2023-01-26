package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.PlayList;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path, String token, Object requestPlayList){

        return given(getRequestSpecBuilder())
                .header("Authorization", "Bearer "+token).
                body(requestPlayList).when()
                .post(path)
                .then()
                .spec(getResponseSpecBuilder())
                .extract().response();

    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccountRequestSpec()).
                formParams(formParams).
                when().post(API + TOKEN).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
    public static Response get(String path, String token){
        return given(getRequestSpecBuilder())
                .header("Authorization", "Bearer "+ token)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpecBuilder())
                .extract().response();
    }
}
