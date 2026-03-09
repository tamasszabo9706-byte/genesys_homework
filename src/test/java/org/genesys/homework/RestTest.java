package org.genesys.homework;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains rest tests.
 */
@Execution(ExecutionMode.CONCURRENT)
public class RestTest {
    private static final Logger logger = LoggerFactory.getLogger(RestTest.class);

    /**
     * This test sends a GET request, parses the response, logs it, and make assertion about it.
     */
    @Test
    public void restAPITesting() throws URISyntaxException, IOException, InterruptedException {
        // Create and send request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://jsonplaceholder.typicode.com/users")).GET().build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        // Parse response body
        JSONArray jsonArray = new JSONArray(response.body());

        // Log names and emails
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            logger.info("{} | {}", jsonObject.getString("name"), jsonObject.getString("email"));
        }

        assertTrue(jsonArray.getJSONObject(0).getString("email").contains("@"), "First email should contain '@'");
    }
}
