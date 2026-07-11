package com.shopkart.ui.pages;

import com.shopkart.config.AppConfig;
import com.shopkart.ui.locators.Xp;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public LoginPage openPage() {
        open(AppConfig.BASE_URL + "/login");
        return this;
    }

    public LoginPage enterEmail(String email) {
        $x(Xp.LOGIN_EMAIL).setValue(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        $x(Xp.LOGIN_PASSWORD).setValue(password);
        return this;
    }

    public HomePage clickSignIn() {
        $x(Xp.SIGN_IN).click();
        return new HomePage();
    }

    public HomePage login(String email, String password) {

        return openPage()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignIn();
    }

}