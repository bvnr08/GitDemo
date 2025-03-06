package JiraBug;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://bvnarasimharao89.atlassian.net";
		String createdBug = given().log().all()
		.header("Content-Type","application/json")
		.header("Authorization","Basic Yi52Lm5hcmFzaW1oYXJhbzg5QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBYSHZ5T2FLaFZMOFFDVFpHLThJaWxoanhsN0RnaGdCaTBQVHh4b2dKeU1LcHo5dlJZS3JIZy1LZ1RzU3Z1ZEdtSXVxT1hNczFrSmpaQUFVSlhnNFFidDhkVEtVUElSZXQyTGcxSHdkY1NveThhbjVwUkFEUjUweUZtb3ctaEh6WF8xWXdyMk5OZlYzbjdpNHNSNGFlcEJTbW9LeUZGR3R0c3hkeDJZYjAtdGM9RUVFMDg3NDY")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Select dropdown not working\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}").when().post("/rest/api/2/issue").then().log().all()
		.assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js= new JsonPath(createdBug);
		String bugId=js.getString("id");
		System.out.println(bugId);
		
		//ADD attachment
		
		given().pathParam("key", bugId).header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic Yi52Lm5hcmFzaW1oYXJhbzg5QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBYSHZ5T2FLaFZMOFFDVFpHLThJaWxoanhsN0RnaGdCaTBQVHh4b2dKeU1LcHo5dlJZS3JIZy1LZ1RzU3Z1ZEdtSXVxT1hNczFrSmpaQUFVSlhnNFFidDhkVEtVUElSZXQyTGcxSHdkY1NveThhbjVwUkFEUjUweUZtb3ctaEh6WF8xWXdyMk5OZlYzbjdpNHNSNGFlcEJTbW9LeUZGR3R0c3hkeDJZYjAtdGM9RUVFMDg3NDY")
		.multiPart("file",new File("E:\\Narasimha\\RestAPI\\images.jpg"))
		.when().post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}

}
