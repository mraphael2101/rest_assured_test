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
    public void testGetPostsWithDeserialization() throws JsonProcessingException {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Mark R");
        data.put("age", 35);

        // Serialize the data to JSON using Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = null;
        try {
            jsonBody = objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post();

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

    }
}
