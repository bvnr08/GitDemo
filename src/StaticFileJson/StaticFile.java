package StaticFileJson;

import static io.restassured.RestAssured.given;


import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticFile {

	
	@Test
	public void addBook() throws IOException {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String response =  given().log().all().header("Content-Type","application/json")
		.body(new String (Files.readAllBytes(Paths.get("E:\\Narasimha\\RestAPI\\AddBook.json"))))
		.when().post("/Library/Addbook.php").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= new JsonPath(response);
		String id=js.get("ID");
		System.out.println(id);
		
		
		
	}
}
