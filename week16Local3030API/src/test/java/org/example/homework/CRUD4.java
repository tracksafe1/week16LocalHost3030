package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUD4 {
    Response response;
    ValidatableResponse validatableResponse;
    RequestSpecification requestSpecification;

    @Test
    public void getAllCategories() {
        response = given().log().all()
                .when()
                .get("http://localhost:3030/categories");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void getSingleCategory() {
        response = given().log().all()
                .when()
                .get("http://localhost:3030/categories/abcat0010000");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void createCategory() {
        String jsondata = "{\n" +
                "  \"name\": \"Pet\",\n" +
                "  \"id\": \"1093\"\n" +
                "}";
        validatableResponse = given()
                .baseUri("http://localhost:3030/categories")
                .contentType(ContentType.JSON)
                .body(jsondata)

                // WHEN
                .when()
                .post()

                // THEN
                .then()
                .statusCode(400);
        // .body("name", equalTo("Pet"));

        System.out.println(validatableResponse.extract().asPrettyString());
    }

    //        response = RestAssured.given().log().all()
//                .contentType(ContentType.JSON)
//                .when()
//                .body(jsondata)
//                .post("http://localhost:3030/categories");
//        response.then().log().all();
//                .statusCode(201)
//                .body("name", equalTo("Pet"));
    @Test
    public void patchCategory() {

        String jsonData = "{\n" +
                "  \"name\": \"Pet\",\n" +
                "  \"id\": \"10933345\"\n" +
                "}";
        given()
                .baseUri("http://localhost:3030/categories/10933345")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .when()
                .patch()
                .then().statusCode(200);
    }

    @Test
    public void deleteCategory() {
        validatableResponse = given()
                .baseUri("http://localhost:3030/categories/10933345")
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .assertThat().statusCode(404);
    }
}




