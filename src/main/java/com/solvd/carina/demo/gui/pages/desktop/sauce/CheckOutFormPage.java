package com.solvd.carina.demo.gui.pages.desktop.sauce;

import com.solvd.carina.demo.gui.pages.common.sauce.CheckOutFormPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckOutFormPage extends CheckOutFormPageBase {
    @FindBy(xpath = "//input[@id = \"first-name\"]")
    private ExtendedWebElement firstNameField;

    @FindBy(xpath = "//input[@id = \"last-name\"]")
    private ExtendedWebElement lastNameField;

    @FindBy(xpath = "//input[@id = \"postal-code\"]")
    private ExtendedWebElement postalCodeField;

    @FindBy(xpath = "//input[@type='submit']")
    private ExtendedWebElement continueButton;

    public CheckOutFormPage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-step-one.html");
    }

    @Override
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        firstNameField.type(firstName);
        lastNameField.type(lastName);
        postalCodeField.type(postalCode);
    }

    @Override
    public void clickContinueButton() {
        continueButton.click();
    }
}
