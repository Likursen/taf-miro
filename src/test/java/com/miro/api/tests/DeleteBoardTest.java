package com.miro.api.tests;

import com.miro.utils.UtilsAPI;
import com.miro.config.annotation.TestAPI;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@TestAPI
@Story(value = "Delete first board checks")
public class DeleteBoardTest extends BaseTest {

    @Test(description = "test delete board")
    public void deleteBoardTest() {
        List<String> allBoardIdBeforeTest = UtilsAPI.getAllBoardId();
        String deleteBoardId = allBoardIdBeforeTest.get(0);
        RestAssured.given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", TOKEN)
                .pathParam("board_id", deleteBoardId)
                .log()
                .everything()
                .when()
                .delete("/v2/boards/{board_id}")
                .then()
                .assertThat()
                .statusCode(204)
                .log()
                .all();
        LOGGER.info("deleted board id: " + deleteBoardId);
        Assert.assertFalse(UtilsAPI.isBoardExist(deleteBoardId), "Deleted board exist");
    }
}