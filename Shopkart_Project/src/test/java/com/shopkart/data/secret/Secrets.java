package com.shopkart.data.secret;

import com.shopkart.config.Env;

public final class Secrets {

    private Secrets() {
    }

    public static String alicePassword() {
        return Env.get("SHOPKART_ALICE_PASSWORD");
    }

    public static String bobPassword() {
        return Env.get("SHOPKART_BOB_PASSWORD");
    }

    public static String carolPassword() {
        return Env.get("SHOPKART_CAROL_PASSWORD");
    }

    public static String tokenSecret() {
        return Env.get("SHOPKART_TOKEN_SECRET");
    }
}