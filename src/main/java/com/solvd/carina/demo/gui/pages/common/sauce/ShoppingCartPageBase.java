package com.solvd.carina.demo.gui.pages.common.sauce;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;


public abstract class ShoppingCartPageBase extends AbstractPage {

    public ShoppingCartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isProductInCart(String productName);

    public abstract void removeProductFromCart(String productName);

    public abstract void clickCheckoutButton();
}

