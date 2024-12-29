package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

@Tag("UI")
public class UiTests extends LoginPage {
    private final String USERNAME = "standard_user";
    private final String PASSWORD = "secret_sauce";

    /*@BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "126";
        Configuration.timeout = 8000; // Увеличение таймаута
        Configuration.browserSize = "1920x1080";
        //WebDriverManager.chromedriver().setup();

    }*/

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "126";
        Configuration.timeout = 8000; // Увеличение таймаута
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
        open(baseUrl);
    }


    @Test
    public void TheUserCanAddAnItemToTheCartTest() {
        openLoginPage();
        login(USERNAME, PASSWORD);

        ProductsPage productsPage = new ProductsPage();
        productsPage.addToCart();
        productsPage.clickIconBascet();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.checkedText1();

    }

}
