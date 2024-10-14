package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

/**
 * This is an exercise to assess your RestAssured skills.
 * Using classes from the above packages starting from line 3 and the website
 * https://jsonplaceholder.typicode.com/
 * 1) Create a RestAssured test that:
    - Sets up a base URL.
    - Sends a GET request to the /posts endpoint.
    - Verifies that the response status code.
    - Extracts the first post's title and body.
    - Asserts that the extracted title and body match expected values.
 * 2) If you complete this are there any improvements you can do to make the test more thorough
 * */

public class APIBasicTestCase {

    @Test
    public void testGetPosts() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        Response response = given()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract().response();

        String firstPostTitle = response.jsonPath().getString("[0].title");
        String firstPostBody = response.jsonPath().getString("[0].body");

        String expectedTitle = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String expectedBody = "quia et suscipit\n" +
                "suscipit recusandae consequuntur expedita et cum\n" +
                "reprehenderit molestiae ut ut quas totam\n" +
                "nostrum rerum est autem sunt rem eveniet architecto";
        
        assertEquals(expectedTitle, firstPostTitle);
        assertEquals(expectedBody, firstPostBody);
    }
}
