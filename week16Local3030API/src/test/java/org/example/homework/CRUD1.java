package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUD1 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllProducts() {

        response = RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/products");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void getProductbyid() {

        response = RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/products/48530");
        response.then().log().all().statusCode(200);
    }


    @Test
    public void postProductInfo() {

        String data = "{\n" +
                "  \"name\": \"Thread\",\n" +
                "  \"type\": \"silk\",\n" +
                "  \"price\": 0,\n" +
                "  \"shipping\": 0,\n" +
                "  \"upc\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"manufacturer\": \"arvind\",\n" +
                "  \"model\": \"string\",\n" +
                "  \"url\": \"string\",\n" +
                "  \"image\": \"string\"\n" +
                "}";
        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(data)
                .post("http://localhost:3030/products");
        response.then().log().all().statusCode(201)
        .body("name",equalTo("Thread"))
        .body("type",equalTo("silk"));

    }
    @Test
    public void PatchProductDetail(){

        String jsondata="{\n" +
                "  \"name\": \"synthetic\"\n" +
                "}";

        String baseUrl = "http://localhost:3030/products/9999690";
        //String endPoint = "/updateProductById";

        //given
        given().log().all()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(jsondata)

                //Then
                .when()
                .patch()
                .then().log().all()
                .body("name", equalTo("synthetic"))
                .statusCode(200);





    }
       @Test
    public void StudentInfoDeleted() {
        given()
                .when()
                .delete("http://localhost:3030/products/9999690")
                .then()
                .statusCode(404);
    }
}



