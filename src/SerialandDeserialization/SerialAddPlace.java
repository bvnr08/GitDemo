package SerialandDeserialization;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import files.PayLoad;

public class SerialAddPlace {
	
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
		
		String res=given().queryParam("key", "qaclick123").log().all()
		.body(p)
		.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).extract().asString();
		
		System.out.println(res);
	}
	


}
