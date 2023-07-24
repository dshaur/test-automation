package com.solvd.carina.demo.gui.pages.desktop.sauce;

import com.solvd.carina.demo.gui.pages.common.sauce.LoginPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends LoginPageBase {

    @FindBy(id = "user-name")
    private ExtendedWebElement usernameInput;

    @FindBy(id = "password")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//div[contains(@class, 'error-message-container')]")
    private ExtendedWebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageURL("");
    }

    @Override
    public void login(String username, String password) {
        usernameInput.type(username);
        passwordInput.type(password);
        loginButton.click();
    }

    @Override
    public boolean isLoginErrorMessagePresent(Long timeout) {
        return loginErrorMessage.isElementPresent(timeout);
    }


}
