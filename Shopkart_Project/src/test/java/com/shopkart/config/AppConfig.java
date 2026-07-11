package com.shopkart.config;

public final class AppConfig {

    private AppConfig() {
    }

    public static final String BASE_URL = "http://localhost:8080";

    public static final String API_BASE_URL = BASE_URL + "/api";

    public static final String BROWSER = "chrome";

    public static final int TIMEOUT = 10000;
}