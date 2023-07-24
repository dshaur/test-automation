package com.solvd.carina.demo.gui.pages.desktop.sauce;

import com.solvd.carina.demo.gui.pages.common.sauce.CheckOutOverviewPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckOutOverviewPage extends CheckOutOverviewPageBase {

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<ExtendedWebElement> productsInCart;

    @FindBy(xpath = "//button[text()='Finish']")
    private ExtendedWebElement finishButton;

    public CheckOutOverviewPage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-step-two.html");
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

    @Override
    public void clickFinishButton() {
        finishButton.click();
    }
}
