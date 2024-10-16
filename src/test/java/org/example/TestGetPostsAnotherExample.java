package org.example;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestGetPostsAnotherExample {

    @Test
    public void testGetPosts() {

        String base_url = "https://jsonplaceholder.typicode.com";
        String endpoint = base_url + "/posts";

        Response response = given().
                when().
                get(endpoint);

        assertThat(response.statusCode(), is(200));

        //response.then().log().all();

        List<String> titles = response.jsonPath().getList("title");

        for (String title : titles) {
            System.out.println(title);
            assertThat(title, notNullValue());
         }

        assertThat(titles.get(1), equalTo("qui est esse"));

    }

}