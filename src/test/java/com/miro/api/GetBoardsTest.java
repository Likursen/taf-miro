package com.miro.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GetBoardsTest extends BaseTest{
    @Test
    public void getAllBoards() {
        RestAssured.baseURI = "https://api.miro.com";
        String s = RestAssured.given()
                .header("Accept", "application/json")
                .header("Authorization", bearer)
                .when()
                .get("/v2/boards?sort=default")
                .then()
                .extract()
                .response()
                .asString();
    }
}
