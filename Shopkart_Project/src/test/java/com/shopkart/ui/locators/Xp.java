package com.shopkart.ui.locators;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public final class Xp {

    private Xp() {
    }

    public static final class Login {

        private Login() {
        }

        public static final String EMAIL =
                "//input[@id='email']";

        public static final String PASSWORD =
                "//input[@id='password']";

        public static final String SIGN_IN =
                "//button[@type='submit' and normalize-space()='Sign in']";
    }

    public static final class Product {

        private Product() {
        }

        public static final String CARD =
                "//div[contains(@class,'product')][.//*[normalize-space()='%s']]";

        public static final String ADD_TO_CART =
                CARD + "//button[normalize-space()='Add to cart']";

        public static SelenideElement card(String productName) {
            return $x(String.format(CARD, productName));
        }

        public static SelenideElement addToCart(String productName) {
            return $x(String.format(ADD_TO_CART, productName));
        }
    }

    public static final class Cart {

        private Cart() {
        }

        public static final String LINE =
                "//tr[contains(@class,'cart-line')][td[normalize-space()='%s']]";

        public static SelenideElement line(String sku) {
            return $x(String.format(LINE, sku));
        }
    }
}