package com.shopkart.api.base;

import com.shopkart.config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseApiClient {

    static {
        RestAssured.baseURI = AppConfig.API_BASE_URL;
    }

    protected RequestSpecification request() {

        return given()
                .contentType("application/json")
                .accept("application/json");
    }

    protected RequestSpecification authenticated(String token) {

        return request()
                .header("Authorization", "Bearer " + token);
    }
}