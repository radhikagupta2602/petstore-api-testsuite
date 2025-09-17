package org.gupta.radhika.petstore.api.testsuite.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {
    @BeforeClass
    public void setUp() {
        // Petstore v2 base URI
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        // optional global settings:
        // RestAssured.port = 443; RestAssured.basePath = "/";
    }
}
