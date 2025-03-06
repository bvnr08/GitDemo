package ApiE2EApp;

import ApiE2EPojo.LoginDetails;
import ApiE2EPojo.LoginResponse;
import ApiE2EPojo.Orders;
import ApiE2EPojo.OrdersDetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;


public class EcomerceAPP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Login 
				
	RequestSpecification req	= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
	
	LoginDetails loginDetails= new LoginDetails();
	loginDetails.setUserEmail("b.v.narasimharao89@gmail.com");
	loginDetails.setUserPassword("Chinni1@");
	
	RequestSpecification loginreq = given().log().all().spec(req).body(loginDetails);
      LoginResponse loginres = loginreq.when().post("/api/ecom/auth/login").then().log().all()
    		  .extract().response().as(LoginResponse.class);
     System.out.println(loginres.getToken());
     System.out.println(loginres.getUserId());
     String token= loginres.getToken();
     String userId=loginres.getUserId();
     
     //Add or create product
     
     RequestSpecification addProcuctBaseRequest	= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
    			.addHeader("Authorization", token).build();
     
     RequestSpecification addProductRequest = given().log().all().spec(addProcuctBaseRequest).param("productName", "LapTop")
     .param("productAddedBy", userId).param("productCategory", "fashion")
     .param("productSubCategory", "shirts").param("productPrice", "11500")
 	 .param("productDescription", "Lenova").param("productFor", "men")
 	 .multiPart("productImage",new File("E:\\Narasimha\\RestAPI\\download.jpeg"));
     
     String addProductResponse = addProductRequest.when().post("/api/ecom/product/add-product").then()
    		 .log().all().extract().response().asString();
     
     JsonPath js= new JsonPath(addProductResponse);
     String productId= js.get("productId");
     
     //Create order
     RequestSpecification createOrderBaseRequest	= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
    			.setContentType(ContentType.JSON).addHeader("Authorization", token)
    			.build();
     
     OrdersDetails ordersDetails= new OrdersDetails();
     ordersDetails.setCountry("India");
     ordersDetails.setProductOrderedId(productId);
     
     List<OrdersDetails> OrdersDetailslist= new ArrayList<OrdersDetails>();
     OrdersDetailslist.add(ordersDetails);
     
     Orders orders= new Orders();
     orders.setOrders(OrdersDetailslist);
     
     
     RequestSpecification createorderRequest =given().log().all().spec(createOrderBaseRequest).body(orders);
     
     String createOrderResponse =createorderRequest.when().post("/api/ecom/order/create-order").then().log().all()
     .extract().response().asString();
     System.out.println(createOrderResponse);
     JsonPath js1= new JsonPath(createOrderResponse);
     String orderss= js1.getString("orders");
     
     //view orders details
     
     RequestSpecification viewOrderBaseRequest	= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
 			.addQueryParam("id", orderss).addHeader("Authorization", token)
 			.build();
    String viewOrderResponse= given().log().all().spec(viewOrderBaseRequest).when().get("/api/ecom/order/get-orders-details")
     .then().log().all().extract().response().asString();
    System.out.println(viewOrderResponse);
    
    //Delete order
    
    RequestSpecification deleteOrderBaseRequest	= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
 			.addHeader("Authorization", token)
 			.build();
    
    RequestSpecification deleteOrderReq =given().log().all().spec(deleteOrderBaseRequest)
    		.pathParam("productId",productId);
    String deleteProductResponse = deleteOrderReq.when().delete("/api/ecom/product/delete-product/{productId}")
    		.then().log().all().
    		extract().response().asString();
    System.out.println(deleteProductResponse);
    JsonPath js2 = new JsonPath(deleteProductResponse);

    Assert.assertEquals("Product Deleted Successfully",js2.get("message"));
    
     
	}

}
