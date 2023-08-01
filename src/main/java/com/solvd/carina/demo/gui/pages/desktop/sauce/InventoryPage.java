package com.solvd.carina.demo.gui.pages.desktop.sauce;

import com.solvd.carina.demo.gui.components.SauceProduct;
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

    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<SauceProduct> products;

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
        for (SauceProduct product : products) {
            if (product.getProductTitle().equals(productName)) {
                product.clickAddToCart();
                break;
            }
        }
    }

    @Override
    public void clickCartIcon() {
        cartIcon.click();
    }
}