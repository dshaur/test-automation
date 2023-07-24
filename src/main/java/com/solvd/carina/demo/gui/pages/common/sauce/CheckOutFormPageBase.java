package com.solvd.carina.demo.gui.pages.common.sauce;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CheckOutFormPageBase extends AbstractPage {
    protected CheckOutFormPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void fillCheckoutForm(String firstName, String lastName, String postalCode);

    public abstract void clickContinueButton();
}
