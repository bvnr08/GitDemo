package oAuth;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import SerialandDeserialization.Api;
import SerialandDeserialization.GetCourses;
import SerialandDeserialization.webAutomation;
import io.restassured.path.json.JsonPath;
public class OAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] expected= {"Selenium Webdriver Java","Cypress","Protractor"} ;
		String response = given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(response);
		JsonPath js= new JsonPath(response);
		String accessToken= js.getString("access_token");
		System.out.println(accessToken);
		
		GetCourses gc = given()
		.queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getExpertise());
		
		List<Api> apiCourse =gc.getCourses().getApi();
		
		for(int i=0; i<apiCourse.size();i++) {
			
			if(apiCourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				
			System.out.println(apiCourse.get(i).getPrice());
				
			}
		}
		
	List<webAutomation> webac=	gc.getCourses().getWebAutomation();
	
		for(int j=0; j<webac.size();j++) {
			if(webac.get(j).getCourseTitle().equalsIgnoreCase("Protractor")) {
				System.out.println(webac.get(j).getPrice());
			}
		}
		
		//Get the course names of WebAutomation
		
		ArrayList<String> ac= new ArrayList<String>();
		
	List<webAutomation> c	=gc.getCourses().getWebAutomation();
	
	for(int z=0;z<c.size();z++)
	{
		ac.add(c.get(z).getCourseTitle());
	}
	
	List<String> expectedList=	Arrays.asList(expected);
	
	Assert.assertTrue(ac.equals(expectedList));
			
	}
	

}
