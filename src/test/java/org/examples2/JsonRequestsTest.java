package org.examples2;

import static io.restassured.RestAssured.baseURI;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.examples2.pojos.MyRequest;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonRequestsTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        baseURI = "https://jsonplaceholder.typicode.com/";
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testPostRequest() throws JsonProcessingException {
        // This example uses the "posts" endpoint which supports POST requests
        String endpoint = "posts";

        // Create a request object (modify fields as needed)
        MyRequest requestData = new MyRequest(
                "New Post Title",
                "This is some post content",
                1  // User ID (modify based on your data)
        );

        // Use Jackson ObjectMapper for JSON serialization
        String jsonContent = objectMapper.writeValueAsString(requestData);

        // Make the POST request using RestAssured
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonContent)
                .post(endpoint);

        // Assert the response status code (assuming success is 201)
        response.then().statusCode(201);  // Assuming successful creation returns 201

        // Process the response body (if needed)
        String responseBody = response.getBody().asString();

        // You can optionally parse the response body using objectMapper
        // MyResponse responseObject = objectMapper.readValue(responseBody, MyResponse.class);

        System.out.println("Response: " + responseBody);
    }


}