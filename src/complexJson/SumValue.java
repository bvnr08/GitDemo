package complexJson;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValue {
	
	@Test
	public void sumOfCources() {
		int sum=0;
		JsonPath js= new JsonPath(PayLoad.parse());
		int count=js.getInt("courses.size()");
		
		for(int i=0;i<count;i++) {
			
		int price=js.getInt("courses["+i+"].price");
		int copies=js.getInt("courses["+i+"].copies");
		int amount= price*copies;
		System.out.println(amount);
		
		
			sum=sum+amount;
		}
		System.out.println(sum);
		
		int puamount=js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, puamount);
		
	}

}
