package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Exception {
		StepDefination defination = new StepDefination();
		if (StepDefination.placeId == null) {
			defination.add_place_payload_with("Test", "English", "India");
			defination.user_calls_with_http_request("AddPlaceAPI", "POST");
			defination.verify_place_id_created_maps_to_using("Test", "getPlaceAPI");
		}
	}
}
