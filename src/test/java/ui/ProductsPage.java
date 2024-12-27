package ui;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {
    private SelenideElement buttonAddToCart = $(Selectors.byId("add-to-cart-sauce-labs-backpack"));
    private SelenideElement iconBascet = $(Selectors.byAttribute("data-test", "shopping-cart-link"));

    public void addToCart() {
        buttonAddToCart.click();
    }

    public void clickIconBascet() {
        iconBascet.click();
    }

}
