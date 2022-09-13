package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String lang, String address) {
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(lang);
		addPlace.setPhone_number("1234567890");
		addPlace.setWebsite("www.google.com");
		addPlace.setName(name);

		ArrayList<String> list = new ArrayList<String>();
		list.add("shop");
		list.add("shop2");

		addPlace.setTypes(list);
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlace.setLocation(location);
		return addPlace;
	}

	public String deletePlacePayload(String placeid) {
		return "{\r\n" + "    \"place_id\":\"" + placeid + "\"\r\n" + "}";
	}

}
