package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification request;
	Response response;
	ResponseSpecification resSpec;
	TestDataBuild testData = new TestDataBuild();
	static String placeId;
	JsonPath path;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws Exception {
		request = given().log().all().spec(requestSpecification())
				.body(testData.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		APIResources resourceAPI = APIResources.valueOf(resource);

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (httpMethod.equalsIgnoreCase("POST"))
			response = request.when().post(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("GET"))
			response = request.when().get(resourceAPI.getResource());
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		response.then().spec(resSpec).extract().response();
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String resp = response.asString();
		System.out.println(resp);
		path = new JsonPath(resp);

		assertEquals(getJsonPath(response, key), value);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Exception {
		placeId = getJsonPath(response, "place_id");
		request = given().log().all().spec(requestSpecification()).queryParam("place_id", placeId);
		this.user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws Exception {
		request = given().spec(requestSpecification()).body(testData.deletePlacePayload(placeId));
	}

}
