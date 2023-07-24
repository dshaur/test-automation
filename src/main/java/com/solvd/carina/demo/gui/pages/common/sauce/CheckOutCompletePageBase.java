package com.solvd.carina.demo.gui.pages.common.sauce;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CheckOutCompletePageBase extends AbstractPage {

    protected CheckOutCompletePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void clickBackHomeButton();
}
