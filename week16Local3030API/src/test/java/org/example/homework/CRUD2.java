package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUD2 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    @Test
    public void getAllStores(){
      response= RestAssured.given().log().all()
              .when()
              .get("http://localhost:3030/stores");
              response.then().log().all().statusCode(200);

    }
    @Test
    public void getByID(){
        response= RestAssured.given().log().all()
                    .when()
                    .get("http://localhost:3030/stores/4");
            response.then().log().all().statusCode(200);

        }
        @Test
    public void createNew(){
        String jsondata="{\n" +
                "  \"name\": \"phone\",\n" +
                "  \"type\": \"Digital\",\n" +
                "  \"address\": \"Borehamwood\",\n" +
                "  \"address2\": \"string\",\n" +
                "  \"city\": \"string\",\n" +
                "  \"state\": \"string\",\n" +
                "  \"zip\": \"string\",\n" +
                "  \"lat\": 0,\n" +
                "  \"lng\": 0,\n" +
                "  \"hours\": \"string\",\n" +
                "    \"services\": {}\n" +
                "}";
        validatableResponse=given().log().all()
                .contentType(ContentType.JSON)
                .body(jsondata)
                .post("http://localhost:3030/stores")
                .then().log().all()
                .statusCode(201)
                .body("name",equalTo("phone"));

        }
        @Test
    public void patchStoreinfo(){
        String jsondata="{\"type\": \"Digital\"}";

            String baseUrl = "http://localhost:3030/stores/8925";
//           Given
            given().log().all()
                    .baseUri(baseUrl)
                    .contentType(ContentType.JSON)
                    .body(jsondata)

                    //Then
                    .when()
                    .patch()
                    .then().log().all()
                    .body("type", equalTo("Digital"))
                    .statusCode(200);


        }
        @Test
    public void deleteStoreInfo(){
            given()
                    . when()
                    .delete("http://localhost:3030/products/9999690")
                    .then()
                    .statusCode(404);



        }


    }


