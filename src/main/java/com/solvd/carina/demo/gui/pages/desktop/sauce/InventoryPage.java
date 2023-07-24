package com.solvd.carina.demo.gui.pages.desktop.sauce;

import com.solvd.carina.demo.gui.components.topmenu.TopMenu;
import com.solvd.carina.demo.gui.pages.common.sauce.InventoryPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends InventoryPageBase {

    @FindBy(xpath = "//div[@class='bm-menu']")
    private TopMenu topMenu;

    @FindBy(xpath = "//div[@class='bm-burger-button']")
    private ExtendedWebElement menuButton;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<ExtendedWebElement> productNames;

    @FindBy(xpath = "//button[@class]")
    private List<ExtendedWebElement> addToCartButtons;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private ExtendedWebElement cartIcon;

    public InventoryPage(WebDriver driver) {
        super(driver);
        setPageURL("inventory.html");
    }


    @Override
    public TopMenu getTopMenu() {
        menuButton.click(5);
        return topMenu;
    }


    @Override
    public void addToCart(String productName) {

        // Search for the product and add it to the cart
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equals(productName)) {
                // Check if the button is a "Remove" button
                if (addToCartButtons.get(i).getText().equals("Remove")) {
                    // If it's a "Remove" button, that means the product is already in the cart, so we skip adding it again
                    break;
                } else {
                    addToCartButtons.get(i).click();
                    break;
                }
            }
        }
    }

    @Override
    public void clickCartIcon() {
        cartIcon.click();
    }
}