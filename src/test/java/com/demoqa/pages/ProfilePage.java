package com.demoqa.pages;

import com.codeborne.selenide.Selenide;
import com.demoqa.models.LoginResponseModel;
import org.openqa.selenium.Cookie;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {
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
        $(byText("Delete All Books")).click();
        $(byText("OK")).click();
        Selenide.confirm();
        return this;
    }


}
