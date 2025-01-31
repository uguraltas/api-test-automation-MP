package steps;

import utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiSteps {

    private static final Logger logger = LogManager.getLogger(ApiSteps.class);
    private RequestSpecification request;
    private Response response;
    private String baseUrl = ConfigReader.getProperty("baseUrl");
    public static String objectId;


    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        logger.info("Sending GET request to: " + baseUrl + endpoint);
        request = given()
                .baseUri(baseUrl);
        response = request.when().get(endpoint);
        logger.info("Response received with status code: " + response.getStatusCode());
        logger.info(String.format("Response body:\n" + response.body().asPrettyString()));
    }

    @Given("I send a POST request to {string} with the following data")
    public void i_send_a_post_request_to_with_the_following_data(String endpoint, String body) {
        logger.info("Sending POST request to: " + baseUrl + endpoint);
        logger.info("Request body: " + body);
        request = given()
                .baseUri(baseUrl)
                .body(body)
                .header("Content-Type", "application/json");
        response = request.when().post(endpoint);
        logger.info("Response received with status code: " + response.getStatusCode());
        logger.info(String.format("Response body:\n" + response.body().asPrettyString()));
        objectId = response.jsonPath().getString("id");
    }

    @Given("I send a PUT request to {string} with the following data")
    public void i_send_a_put_request_to_with_the_following_data(String endpoint, String body ) {
        i_send_a_post_request_to_with_the_following_data("objects", "{\n" +
                "        \"name\": \"Apple MacBook Pro 16\",\n" +
                "        \"data\": {\n" +
                "          \"year\": 2019,\n" +
                "          \"price\": 1849.99,\n" +
                "          \"CPU model\": \"Intel Core i9\",\n" +
                "          \"Hard disk size\": \"1 TB\"\n" +
                "        }\n" +
                "      }");
        endpoint = endpoint.replace("{{savedID}}",objectId);
        logger.info("Sending PUT request to: " + baseUrl + endpoint);
        logger.info("Request body: " + body);
        request = given()
                .baseUri(baseUrl)
                .body(body)
                .header("Content-Type", "application/json");
        response = request.when().put(endpoint);
        logger.info("Response received with status code: " + response.getStatusCode());
        logger.info(String.format("Response body:\n" + response.body().asPrettyString()));
    }

    @Given("I send a PATCH request to {string} with the following data")
    public void i_send_a_patch_request_to_with_the_following_data(String endpoint, String body) {
        if (objectId==null) {
            objectId = "1";
        }
        if(!Hook.scenarioName.contains("fail") && objectId.equals("1")) {
            i_send_a_post_request_to_with_the_following_data("objects", body);
        }
        endpoint = endpoint.replace("{{savedID}}",objectId);
        logger.info("Sending PUT request to: " + baseUrl + endpoint);
        logger.info("Request body: " + body);
        request = given()
                .baseUri(baseUrl)
                .body(body)
                .header("Content-Type", "application/json");
        response = request.when().patch(endpoint);
        logger.info("Response received with status code: " + response.getStatusCode());
        logger.info(String.format("Response body:\n" + response.body().asPrettyString()));
    }

    @Given("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        if (objectId==null) {
            objectId = "1";
        }
        if(!Hook.scenarioName.contains("fail") && objectId.equals("1")) {
            i_send_a_post_request_to_with_the_following_data("objects", "{\n" +
                    "        \"name\": \"Apple MacBook Pro 16\",\n" +
                    "        \"data\": {\n" +
                    "          \"year\": 2019,\n" +
                    "          \"price\": 1849.99,\n" +
                    "          \"CPU model\": \"Intel Core i9\",\n" +
                    "          \"Hard disk size\": \"1 TB\"\n" +
                    "        }\n" +
                    "      }");
        }
        endpoint = endpoint.replace("{{savedID}}",objectId);
        logger.info("Sending DELETE request to: " + baseUrl + endpoint);
        request = given()
                .baseUri(baseUrl);
        response = request.when().delete(endpoint);
        logger.info("Response received with status code: " + response.getStatusCode());
        logger.info(String.format("Response body:\n" + response.body().asPrettyString()));
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        logger.info("Validating response status code: " + statusCode);
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain all objects")
    public void the_response_should_contain_all_objects() {
        logger.info("Validating response contains all objects");
        response.then().body("size()", greaterThan(0));
    }

    @Then("the response should contain the object with id {string}")
    public void the_response_should_contain_the_object_with_id(String id) {
        logger.info("Validating response contains object with id: " + id);
        response.then().body("id", equalTo(id));
    }

    @Then("the response should contain the created object")
    public void the_response_should_contain_the_created_object() {
        logger.info("Validating response contains the created object");
        response.then().body("id", notNullValue());
    }

    @Then("the response should contain the updated object")
    public void the_response_should_contain_the_updated_object() {
        logger.info("Validating response contains the updated object");
        response.then().body("id", notNullValue());
    }
}