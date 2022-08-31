package com.miro.utils;

import com.miro.api.pojo.BoardPojo;
import com.miro.api.pojo.BoardsPojo;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class UtilsAPI {
    protected static final Property properties = PropertiesLoader.getProperties();
    private static final String TOKEN = properties.token();

    public static List<String> getAllBoardId() {
        return getAllBoards()
                .getData()
                .stream()
                .map(BoardsPojo.Datum::getId)
                .collect(Collectors.toList());
    }

    public static boolean isBoardExist(String id) {
        return getStatusCodeByGettingSpecificBoard(id) == 200;
    }

    public static BoardPojo getBoardByID(String id) {
        return given()
                .header("Accept", "application/json")
                .header("Authorization", TOKEN)
                .pathParam("board_id", id)
                .when()
                .get("/v2/boards/{board_id}")
                .then()
                .extract()
                .as(BoardPojo.class);
    }

    public static String generateJsonFromObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private static BoardsPojo getAllBoards() {
        return given()
                .header("Accept", "application/json")
                .header("Authorization", TOKEN)
                .when()
                .get("/v2/boards?limit=50&sort=default")
                .then()
                .extract()
                .as(BoardsPojo.class);
    }

    private static int getStatusCodeByGettingSpecificBoard(String id) {
        return given()
                .header("Accept", "application/json")
                .header("Authorization", TOKEN)
                .pathParam("board_id", id)
                .when()
                .get("/v2/boards/{board_id}")
                .then()
                .extract()
                .statusCode();
    }
}