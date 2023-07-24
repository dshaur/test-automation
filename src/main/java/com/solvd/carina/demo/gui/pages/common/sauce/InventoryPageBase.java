package com.solvd.carina.demo.gui.pages.common.sauce;

import com.solvd.carina.demo.gui.components.topmenu.TopMenuBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;


public abstract class InventoryPageBase extends AbstractPage {

    public InventoryPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract TopMenuBase getTopMenu();

    public abstract void addToCart(String productName);

    public abstract void clickCartIcon();
}

