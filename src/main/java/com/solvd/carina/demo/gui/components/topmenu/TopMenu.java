package com.solvd.carina.demo.gui.components.topmenu;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TopMenu extends TopMenuBase {

    @FindBy(id = "logout_sidebar_link")
    private ExtendedWebElement logoutLink;

    public TopMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    @Override
    public void logout() {
        logoutLink.click();
    }
}
