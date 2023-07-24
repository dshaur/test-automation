package com.solvd.carina.demo.gui.components.topmenu;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class TopMenuBase extends AbstractUIObject {
    protected TopMenuBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract void logout();
}
