package DynamicJson;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamnicJson {
	
	@Test(dataProvider ="BooksData" )
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String response =  given().log().all().header("Content-Type","application/json")
		.body(PayLoad.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= new JsonPath(response);
		String id=js.get("ID");
		System.out.println(id);
		
		
		
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		
		return new Object[][] {{"vela","456"},{"lavnar","9665"},{"veralav","4567"}};
	}

}
