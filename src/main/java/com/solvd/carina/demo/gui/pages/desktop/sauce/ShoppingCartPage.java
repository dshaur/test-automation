package com.solvd.carina.demo.gui.pages.desktop.sauce;

import com.solvd.carina.demo.gui.pages.common.sauce.ShoppingCartPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends ShoppingCartPageBase {

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<ExtendedWebElement> productsInCart;

    @FindBy(xpath = "//button[@class='btn btn_secondary btn_small cart_button']")
    private List<ExtendedWebElement> removeButtons;

    @FindBy(xpath = "//button[contains(text(), 'Checkout')]")
    private ExtendedWebElement checkoutButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        setPageURL("cart.html");
    }

    @Override
    public boolean isProductInCart(String productName) {
        for (ExtendedWebElement product : productsInCart) {
            if (product.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void removeProductFromCart(String productName) {
        for (int i = 0; i < productsInCart.size(); i++) {
            if (productsInCart.get(i).getText().equals(productName)) {
                removeButtons.get(i).click();
                break;
            }
        }
    }

    @Override
    public void clickCheckoutButton() {
        checkoutButton.click();
    }
}


