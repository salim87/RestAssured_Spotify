package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.PlayList;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpecBuilder;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecBuilder;
import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class spotifyPlaylists {

    PlayList requestPlaylist = new PlayList();

    @Test
    public void createPlaylist(){


        //Builder pattern
        requestPlaylist.setName("New Playlist")
                .setDescription("New playlist description")
                .setPublic(false);
        Response response = PlayListApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(201));

        PlayList responsePlaylist = response.as(PlayList.class);

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));

    }

    @Test
    public void getPlaylist(){

        Response response = PlayListApi.get("68OQWiYGubcnqsvF5Uzyky");

        assertThat(response.statusCode(), equalTo(200));

        PlayList responsePlaylist = response.as(PlayList.class);
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));

    }
}
