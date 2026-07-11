package com.shopkart.config;

public final class Env {

    private Env() {
    }

    public static String get(String key) {

        String value = System.getenv(key);

        if (value == null || value.isBlank()) {
            value = System.getProperty(key);
        }

        if (value == null) {
            throw new IllegalArgumentException(
                    "Missing environment variable: " + key);
        }

        return value;
    }
}