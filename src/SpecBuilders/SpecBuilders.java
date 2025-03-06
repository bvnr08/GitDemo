package SpecBuilders;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import SerialandDeserialization.Addplace;
import SerialandDeserialization.Location;
import files.PayLoad;

public class SpecBuilders {
	
	public static void main(String[] args) {
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		Addplace p =new Addplace();
		p.setAccuracy(50);
		p.setAddress("40, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("NARASIMHA");
		p.setPhone_number("(+91) 966 893 3937");
		p.setWebsite("http://narasimharao.com");
		
		List<String> types= new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		p.setTypes(types);
		
		Location l= new Location();
		l.setLat(-35.383494);
		l.setLng(34.427362);
		p.setLocation(l);
		
		RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification res =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification reqq =given().spec(req)
		.body(p);
		Response response=reqq.when().post("maps/api/place/add/json").then().spec(res).extract().response();
		
		String responseString=response.asString();
		System.out.println(responseString);
		
		
	}
	


}
