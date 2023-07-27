package com.solvd.carina.demo.mobile.gui.pages.common.calendar;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class EventPageBase extends AbstractPage {
    protected EventPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void deleteEvent();

    public abstract boolean isEventPresent(String eventName);
}
