package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class APITestCaseWithJsonSerialization {

    @Test
    public void testPostRequestWithSerialization() throws JsonProcessingException {
        // Create a Map representing the data for a new post
        Map<String, Object> data = new HashMap<>();
        data.put("title", "New Post Title");
        data.put("body", "This is some post content");
        data.put("userId", 1);

        // Serialize the data to JSON using Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(data);

        // This example uses the "posts" endpoint which supports POST requests
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post("posts");  // Using the "posts" endpoint for creating posts

        // Assert the successful response code (assuming success is 201)
        assertEquals(201, response.statusCode());

        System.out.println("Response: " + response.body().asString());
    }
}