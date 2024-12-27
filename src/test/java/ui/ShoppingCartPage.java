package ui;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage {
    private SelenideElement checkedText = $(Selectors.byAttribute("data-test", "inventory-item-name"));

    public void checkedText1() {
        checkedText.shouldHave(text("Sauce Labs Backpack"));
    }

}
