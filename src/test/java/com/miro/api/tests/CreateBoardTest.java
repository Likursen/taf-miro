package com.miro.api.tests;

import com.miro.api.pojo.BoardPojo;
import com.miro.api.requests.DefaultCreateBoardPayload;
import com.miro.utils.Utils;
import com.miro.utils.UtilsAPI;
import com.miro.config.annotation.TestAPI;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

@TestAPI
@Story("Create board checks")
public class CreateBoardTest extends BaseTest {

    @Test(description = "test create board")
    public void createBoardTest() {
        String requestBoardName = Utils.generateRandomSting();
        DefaultCreateBoardPayload boardPayload = new DefaultCreateBoardPayload()
                .setName(requestBoardName);
        LOGGER.info("Request board name: " + requestBoardName);
        String createBoardJson = UtilsAPI.generateJsonFromObject(boardPayload);
        ValidatableResponse response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", TOKEN)
                .body(createBoardJson)
                .log()
                .everything()
                .when()
                .post("/v2/boards")
                .then()
                .assertThat()
                .statusCode(201)
                .log()
                .everything();
        String responseBoardName = response.extract()
                .jsonPath()
                .get("name");
        LOGGER.info("Response board name: " + responseBoardName);
        Assert.assertEquals(responseBoardName, requestBoardName, "Board name in request and response not equals");
        String responseBoardId = response.extract()
                .jsonPath()
                .get("id");
        LOGGER.info("Expected board ID: " + responseBoardId);
        BoardPojo boardByID = UtilsAPI.getBoardByID(responseBoardId);
        LOGGER.info("Expected ID: " + responseBoardId);
        Assert.assertEquals(boardByID.getName(), requestBoardName, "Actual and expected board names not equals");
    }
}