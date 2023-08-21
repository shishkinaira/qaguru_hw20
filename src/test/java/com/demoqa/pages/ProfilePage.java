package com.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.tests.TestBase;
import org.openqa.selenium.Cookie;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage extends TestBase {
    SelenideElement
            buttonText = $(byText("Delete All Books")),
            confirmPopupText = $(byText("OK")),
            noBooksPopupText = $(byText("All Books deleted."));
    public ProfilePage openProfile (LoginResponseModel loginResponseModel){

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponseModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponseModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponseModel.getExpires()));
        return this;
    }

    public ProfilePage delAllBooksS (){

        open("/profile");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        buttonText.click();
        confirmPopupText.click();
        Selenide.confirm();
        return this;
    }

    public ProfilePage checkNoBooksInProfile (){
        buttonText.click();
        noBooksPopupText.exists();
        return this;
    }
}

