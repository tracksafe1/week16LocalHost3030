package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CRUD3 {
    ValidatableResponse validatableResponse;
    Response response;
    RequestSpecification requestSpecification;
    @Test
    public void getAllservicesinfo(){
        response= RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/services");
        response.then().log().all().statusCode(200);

    }
    @Test
    public void getServicesByID(){
        response=RestAssured.given().log().all()
                .when()
                .get("http://localhost:3030/services/4");
                response.then().log().all().statusCode(200);
    }
    @Test
    public void createService(){
        String jsondata="{\n" +
                "  \"name\": \"PetWalkers\"\n" +
                "}";
        validatableResponse=given().log().all()
                .contentType(ContentType.JSON)
                .body(jsondata)
                .post("http://localhost:3030/services")
                .then().log().all()
                .statusCode(201)
        .body("name",equalTo("PetWalkers"));

    }
    @Test
    public void patchService(){
        String jsondata="{\n" +
                "  \"name\": \"PetFriendlyTaxiServices\"\n" +
                "}";
        String baseurl="http://localhost:3030/services/23";
        //given
        given().log().all()
                .baseUri(baseurl)
                .contentType(ContentType.JSON)
                .body(jsondata)
        //then
                .when()
                .patch()
                .then().log().all()
                .statusCode(200)
                .body("name",equalTo("PetFriendlyTaxiServices"));

    }
    @Test
    public void deleteService(){
        given()
                . when()
                .delete("http://localhost:3030/services/23")
                .then()
                .statusCode(404);


    }



}
