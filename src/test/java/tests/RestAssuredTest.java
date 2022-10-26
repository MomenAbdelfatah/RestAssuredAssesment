package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestAssuredTest {
	
	@Test
	public  void getPostID() {
		baseURI = "https://jsonplaceholder.typicode.com";
		given().
		get("/posts/4/comments").
		then().log().body();
	}
	
	@Test
	public void albumTitle() {
		baseURI = "https://jsonplaceholder.typicode.com";
		Response response = given().
		get("/albums?userId=1").then().
		extract().response();
		int size = response.jsonPath().getString("title").length();
		Assert.assertTrue(size<300);
		
	}
	@Test
	public void postUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		JSONObject req = new JSONObject(map);
		
		req.put("title", "Test");
		req.put("body", "This is a POST request Test");
		
		baseURI = "https://jsonplaceholder.typicode.com";

		given().
		body(req.toJSONString()).
		when().
		post("/posts?userId=1").
		then().statusCode(201)
		.log().all();
		
		
		
	}
	@Test
	public void printTitlesInTodp() {
		
		List<String> title = null;
		
		baseURI = "https://jsonplaceholder.typicode.com";
		Response response = given().
		get("/todos?userId=1").then().
		extract().response();
		
		title = response.jsonPath().getList("findAll{it.completed==false}.title");
		
		System.out.println(title);
	    
	}
}
