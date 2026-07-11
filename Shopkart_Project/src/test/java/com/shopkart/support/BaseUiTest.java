package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.shopkart.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;

public class BaseUiTest {

    @BeforeEach
    void setUp() {

        Configuration.browser = AppConfig.BROWSER;

        Configuration.baseUrl = AppConfig.BASE_URL;

        Configuration.timeout = AppConfig.TIMEOUT;

        Configuration.headless = false;
    }

}