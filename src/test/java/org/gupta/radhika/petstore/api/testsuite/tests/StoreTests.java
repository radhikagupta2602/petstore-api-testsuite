package org.gupta.radhika.petstore.api.testsuite.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import org.gupta.radhika.petstore.api.testsuite.base.TestBase;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
//import io.restassured.matcher.ResponseAwareMatcher;
//import io.restassured.response.Response;

public class StoreTests extends TestBase {

	@Test
	public void addGetUpdateDeleteStore_flow() {
		  
		  String orderPayload = "{ \"id\": 1001, \"petId\": 12345, \"quantity\": 1, \"status\": \"placed\" }";
		  
		  //placeorder_shouldReturn200
		  given()
		       .contentType(ContentType.JSON)
		       .body(orderPayload)
		  .when()
		       .post("/store/order")
		  .then()
		       .statusCode(200)
		       .body("id", equalTo(1001))
	           .body("status", equalTo("placed")); 
		  
		  //getOrderById_shouldReturnOrder
		  when()
              .get("/store/order/1001")
          .then()
              .statusCode(200)
              .body("id", equalTo(1001));
		  
		  //deleteOrder_shouldReturn200
		  when()
              .delete("/store/order/1001")
          .then()
              .statusCode(200);
		  
		  //getInventory_shouldReturn200
		  when()
              .get("/store/inventory")
          .then()
              .statusCode(200)
              .body("available", greaterThanOrEqualTo(0));
		  
	  }
}
