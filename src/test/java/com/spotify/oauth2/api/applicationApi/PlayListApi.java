package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.PlayList;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpecBuilder;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecBuilder;
import static com.spotify.oauth2.api.TokenManager.renewToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PlayListApi {

 //   static String access_token= "BQBcXn7N3xLuozZAxLQLrbO35FNiBDSh6FBIjoTbS8dh9_j2eNRQYKf9-fhlPosSu_zBKvSeypneR4nrJWCglRmvAK_hZFMI8BjKU0Umauh0-rcBmkyLfVXmohICIZrddXKrJEjicjEcw7p168lef6cZAzxwmoMQBmj0gIUGzfbLSU0LrbRHzGKSCE_zanOwkFGHZr1hcpMspN0d5NONKKvYMbyDQm_VynrSNoREbAcpPDOTXU9xkSkaAY1DkILtaBo8";

    public static Response post(PlayList requestPlayList){

        return RestResource.post("/users/31jgmdprwhu32t2yg4cjan2tbhxe/playlists", renewToken(), requestPlayList);

    }

    public static Response get(String playListId){
        return RestResource.get("/playlists/"+playListId, renewToken());

    }
}
