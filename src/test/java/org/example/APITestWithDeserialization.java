package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class APITestWithDeserialization {
    @Test
    public void RegistrationSuccessful() throws JsonProcessingException {
        RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/posts");
        ResponseBody body = response.getBody();


        if(response.statusCode() == 200) {
            // Deserialize the Response body into a list of custom objects of type MyPOJO
            // ObjectMapper converts the response body (in string format) into a list of MyPOJO objects
            ObjectMapper objectMapper = new ObjectMapper();
            List<MyPOJO> myPojoList = objectMapper.readValue(
                    response.body().asString(),
                    new TypeReference<List<MyPOJO>>() {}
            );

            int id = myPojoList.get(0).getId();
            int userId = myPojoList.get(0).getUserId();
            String title = myPojoList.get(0).getTitle();
            String rBody = myPojoList.get(0).getBody();

            assertEquals(1, id);
            assertEquals(1, userId);
            assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", title);
            assertEquals("quia et suscipit\n" +
                            "suscipit recusandae consequuntur expedita et cum\n" +
                            "reprehenderit molestiae ut ut quas totam\n" +
                            "nostrum rerum est autem sunt rem eveniet architecto",
                    rBody);
        }
    }
}
