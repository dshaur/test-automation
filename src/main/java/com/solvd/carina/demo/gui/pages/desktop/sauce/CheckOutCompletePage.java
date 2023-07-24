package com.solvd.carina.demo.gui.pages.desktop.sauce;

import com.solvd.carina.demo.gui.pages.common.sauce.CheckOutCompletePageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckOutCompletePage extends CheckOutCompletePageBase {

    @FindBy(xpath = "//button[text()='Back Home']")
    private ExtendedWebElement backHomeButton;

    public CheckOutCompletePage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-complete.html");
    }

    @Override
    public void clickBackHomeButton() {
        backHomeButton.click();
    }
}
