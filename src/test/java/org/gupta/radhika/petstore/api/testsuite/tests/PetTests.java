package org.gupta.radhika.petstore.api.testsuite.tests;

import io.restassured.http.ContentType;
import org.gupta.radhika.petstore.api.testsuite.base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
public class PetTests extends TestBase {

    @Test
    public void addGetUpdateDeletePet_flow() {
        // use a reasonably unique id so runs don't clash
        long petId = System.currentTimeMillis() % 1000000L; // simpler unique id

        String createPayload = String.format(
            "{\"id\": %d, \"name\":\"doggie-%d\", \"status\":\"available\"}",
            petId, petId
        );

        // CREATE
        given()
            .contentType(ContentType.JSON)
            .body(createPayload)
        .when()
            .post("/pet")
        .then()
            .statusCode(200)
            .body("id", equalTo((int)petId)); // cast to int for JSON number

        // GET
        when()
            .get("/pet/{id}", petId)
        .then()
            .statusCode(200)
            .body("name", containsString("doggie"));

        // UPDATE (change status)
        String updatePayload = String.format(
            "{\"id\": %d, \"name\":\"doggie-%d\", \"status\":\"sold\"}",
            petId, petId
        );

        given()
            .contentType(ContentType.JSON)
            .body(updatePayload)
        .when()
            .put("/pet")
        .then()
            .statusCode(200)
            .body("status", equalTo("sold"));

        // DELETE
        when()
            .delete("/pet/{id}", petId)
        .then()
            .statusCode(200);
    }
}
