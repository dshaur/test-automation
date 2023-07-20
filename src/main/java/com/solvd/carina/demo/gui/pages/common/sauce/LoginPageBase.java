package com.solvd.carina.demo.gui.pages.common.sauce;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;


public abstract class LoginPageBase extends AbstractPage {

    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    // Web elements and actions related to the login page

    public abstract void login(String username, String password);

    public abstract boolean isLoginErrorMessagePresent();

    public abstract String getLoginErrorMessage();
}
