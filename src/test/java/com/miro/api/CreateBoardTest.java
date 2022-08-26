package com.miro.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miro.api.payloads.DefaultBoardPayload;
import com.miro.utils.Utils;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBoardTest extends BaseTest {

    @Test
    public void createBoard() throws JsonProcessingException {
        DefaultBoardPayload boardPayload = new DefaultBoardPayload().setName(Utils.generateRandomSting());
        ObjectMapper objectMapper = new ObjectMapper();
        String createBoardJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(boardPayload);
        RestAssured.baseURI = "https://api.miro.com";
        String actualName = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", bearer)
                .body(createBoardJson)
                .when()
                .post("/v2/boards")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getString("name");
        Assert.assertEquals(actualName, boardPayload.getName());
    }
}
