package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private static final String BASE_URL = "https://www.saucedemo.com/";

    private SelenideElement userNameInput = $(Selectors.byId("user-name"));
    private SelenideElement passwordInput = $(Selectors.byId("password"));
    private SelenideElement loginButton = $(Selectors.byId("login-button"));

    public void openLoginPage() {
        open(BASE_URL);
    }

    public void login(String userName, String password) {
        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        loginButton.click();
    }


}
