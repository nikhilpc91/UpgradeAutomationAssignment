package tests;
import org.json.*;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apitest {

	@Test(priority=1, description="Happy path to verify the status code")
	public void happyPath() 
	{ 
		RestAssured.baseURI = "https://credapi.credify.tech"; 
		RequestSpecification request = RestAssured.given();
		// JSONObject is a class that represents a Simple JSON. 
		// We can add Key - Value pairs using the put method 
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("loanAppUuid", "b8096ec7-2150-405f-84f5-ae99864b3e96"); 
		requestParams.put("skipSideEffects", "true"); 
		// Add a header stating the Request body is a JSON 
		request.header("Content-Type", "application/json");
		request.header("x-cf-source-id", "coding-challenge");
		request.header("x-cf-corr-id", "41599320-62a8-4abb-b02a-f9afb77b55c2");
		// Add the Json to the body of the request 
		request.body(requestParams.toString()); // Post the request and check the response
		Response response = request.post("/api/brfunnelorch/v2/resume/byLeadSecret"); 
		System.out.println("The status received: " + response.statusLine());
		System.out.println(response.statusCode());
		Assert.assertTrue(200==(response.statusCode()), "Expected status code was 200 but actual was "+response.statusCode());
	}
	@Test(priority=2, description="Validate productType attribute from response")
	public void validateResponseAttribute() 
	{ 
		RestAssured.baseURI = "https://credapi.credify.tech"; 
		RequestSpecification request = RestAssured.given();
		// JSONObject is a class that represents a Simple JSON. 
		// We can add Key - Value pairs using the put method 
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("loanAppUuid", "b8096ec7-2150-405f-84f5-ae99864b3e96"); 
		requestParams.put("skipSideEffects", "true"); 
		// Add a header stating the Request body is a JSON 
		request.header("Content-Type", "application/json");
		request.header("x-cf-source-id", "coding-challenge");
		request.header("x-cf-corr-id", "41599320-62a8-4abb-b02a-f9afb77b55c2");
		// Add the Json to the body of the request 
		request.body(requestParams.toString()); // Post the request and check the response
		Response response = request.post("/api/brfunnelorch/v2/resume/byLeadSecret"); 

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		// Then simply query the JsonPath object to get a String value of the node
		String productType = jsonPathEvaluator.get("loanAppResumptionInfo.productType");

		// Validate the response and status code
		Assert.assertEquals(productType, "PERSONAL_LOAN", "Correct productType name received in the Response");
		Assert.assertTrue(200==(response.statusCode()), "Expected status code was 200 but actual was "+response.statusCode());

	}
	
	@Test(priority=3, description="Wrong UUID to verify 404 error")
	public void checkDiffrentUUIDForNotFound() 
	{ 
		RestAssured.baseURI = "https://credapi.credify.tech"; 
		RequestSpecification request = RestAssured.given();
		// JSONObject is a class that represents a Simple JSON. 
		// We can add Key - Value pairs using the put method 
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("loanAppUuid", "b8096ec7-2150-405f-84f5-ae99864b3e88"); 
		requestParams.put("skipSideEffects", "true"); 
		// Add a header stating the Request body is a JSON 
		request.header("Content-Type", "application/json");
		request.header("x-cf-source-id", "coding-challenge");
		request.header("x-cf-corr-id", "41599320-62a8-4abb-b02a-f9afb77b55c2");
		// Add the Json to the body of the request 
		request.body(requestParams.toString()); // Post the request and check the response
		Response response = request.post("/api/brfunnelorch/v2/resume/byLeadSecret"); 
		System.out.println("The status received: " + response.statusLine());
		System.out.println(response.statusCode());
		Assert.assertTrue(404==(response.statusCode()), "Expected status code was 404 but actual was "+response.statusCode());
	}
	@Test(priority=4, description="incorrect format uuid to verify system error")
	public void checkServerErrorStatus() 
	{ 
		RestAssured.baseURI = "https://credapi.credify.tech"; 
		RequestSpecification request = RestAssured.given();
		// JSONObject is a class that represents a Simple JSON. 
		// We can add Key - Value pairs using the put method 
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("loanAppUuid", "b8096ec7-2150-405f-84f5-ae99864b3t96");
		requestParams.put("skipSideEffects", "true"); 
		// Add a header stating the Request body is a JSON 
		request.header("Content-Type", "application/json");
		request.header("x-cf-source-id", "coding-challenge");
		request.header("x-cf-corr-id", "41599320-62a8-4abb-b02a-f9afb77b55c2");
		// Add the Json to the body of the request 
		request.body(requestParams.toString()); // Post the request and check the response
		Response response = request.post("/api/brfunnelorch/v2/resume/byLeadSecret"); 
		System.out.println("The status received: " + response.statusLine());
		System.out.println(response.statusCode());
		Assert.assertTrue(500==(response.statusCode()), "Expected status code was 500 but actual was "+response.statusCode());
	}

}