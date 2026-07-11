package com.shopkart.data.db;

import com.shopkart.config.Env;

public final class DatabaseConfig {

    private DatabaseConfig() {}

    public static String url() {
        return String.format(
                "jdbc:mysql://%s:%s/%s",
                Env.get("DB_HOST"),
                Env.get("DB_PORT"),
                Env.get("DB_NAME")
        );
    }

    public static String username() {
        return Env.get("DB_USER");
    }

    public static String password() {
        return Env.get("DB_PASSWORD");
    }
}