package com.demoqa.tests;

import com.codeborne.selenide.Condition;
import com.demoqa.api.AuthorizationApi;
import com.demoqa.models.CredentialsModel;
import com.demoqa.models.LoginResponseModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demoqa.tests.TestData.credentials;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginTests extends TestBase {


    @Test
    void successfulLoginTest() {
        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        open("/profile");
        $("#userName-value").shouldHave(text(credentials.getUserName()));

    }
}
