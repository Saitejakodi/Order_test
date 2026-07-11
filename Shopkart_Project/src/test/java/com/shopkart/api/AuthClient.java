package com.shopkart.api;

import com.shopkart.api.base.BaseApiClient;
import io.restassured.response.Response;

import java.util.Map;

public class AuthClient extends BaseApiClient {

    public Response login(String email, String password) {

        return request()
                .body(Map.of(
                        "email", email,
                        "password", password))
                .post("/auth/login");
    }

}