package com.solvd.carina.demo.gui.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SauceProduct extends AbstractUIObject {
    @FindBy(xpath = ".//div[@class='inventory_item_name']")
    private ExtendedWebElement productTitle;

    @FindBy(xpath = ".//div[@class='inventory_item_price']")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = ".//button[@class='btn btn_primary btn_small btn_inventory']")
    private ExtendedWebElement addToCartBtn;


    public SauceProduct(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }


    public void clickAddToCart() {
        addToCartBtn.click();
    }
}
