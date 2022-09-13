package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqSpec;

	public RequestSpecification requestSpecification() throws Exception {
		if (reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
			return reqSpec;
		}
		return reqSpec;
	}

	public String getGlobalValue(String key) throws Exception {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				"C:\\Users\\sangram.haladkar\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");

		properties.load(fileInputStream);
		return properties.getProperty(key);
	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath path = new JsonPath(resp);
		return path.get(key).toString();
	}
}
