Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline:  Verify if place is being successfully added using AddPlace API
					Given Add Place Payload with "<name>" "<language>" "<address>"
					When user calls "AddPlaceAPI" with "Post" http request
					Then the API call got success with status code 200
					And "status" in response body is "OK" 
					And "scope" in response body is "APP" 
					And verify place_id created maps to "<name>" using "getPlaceAPI"
					
Examples: 
			|name     | language | address 			 |
			|AAHouse  | English  | Kolhapur      |
#			|BBHouse  | Marathi  | Pune					 |  

@DeletePlace @Regression
Scenario: Verify if Delete place functionality is working

				Given DeletePlace Payload 
				When user calls "DeletePlaceApi" with "POST" http request
				Then the API call got success with status code 200
				And "status" in response body is "OK"