package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecBuilder;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public static String getToken(){

        try {
            if(access_token==null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing Token .......");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                Instant.now().plusSeconds(expiryDurationInSeconds-300);
            }
            else{
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ABORT! failed to get the token");
        }
        return access_token;
    }
    public static Response renewToken(){
        HashMap <String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", "");
        formParams.put("client_secret", "");
        formParams.put("refresh_token", "");
        formParams.put("grant_type", "");

        Response response = given().baseUri("")
                .contentType(ContentType.JSON)
                .formParams(formParams)
                .when()
                .post("")
                .then().spec(getResponseSpecBuilder())
                .extract().response();
        if(response.statusCode() != 200){
            throw  new RuntimeException("ABORT !!! Renew Token failed");

        }
        return response.path("access_token");

    }

}
