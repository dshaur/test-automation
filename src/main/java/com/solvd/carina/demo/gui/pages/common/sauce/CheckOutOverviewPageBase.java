package com.solvd.carina.demo.gui.pages.common.sauce;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CheckOutOverviewPageBase extends AbstractPage {
    protected CheckOutOverviewPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isProductInCart(String productName);

    public abstract void clickFinishButton();
}
