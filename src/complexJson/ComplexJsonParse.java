package complexJson;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
		JsonPath js= new JsonPath(PayLoad.parse());
		
		//Print No of courses returned by API
		
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		
		int purchaseAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		//Print Title of the first course
		
		System.out.println(js.getString("courses[0].title").toString());
		
		//Print All course titles and their respective Prices
		
		for(int i=0; i<count;i++) {
			
		String courseTitle=	js.getString("courses["+i+"].title");
		System.out.println(courseTitle);
		
		System.out.println(js.getString("courses["+i+"].price").toString());
			break;
		}

		//Print no of copies sold by RPA Course
		for(int i=0;i<count;i++) {
			String courseTitles=	js.getString("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA")) {
				System.out.println(js.getString("courses["+i+"].copies").toString());
			}
		}
		
	}

}
