package com.solvd.carina.demo.mobile.gui.pages.common.calendar;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CalendarPageBase extends AbstractPage {
    protected CalendarPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void clickCreateEventBtn();

    public abstract void clickJumpToTodayBtn();

    public abstract void clickCreateNewBtn();

    public abstract void clickSearchBtn();

    public abstract boolean isEventPresent(String eventTitle);

    public abstract boolean isEventPresent(String eventTitle, String color);

    public abstract void selectEvent(String eventTitle);

    public abstract void clickCancel();

    public abstract void scrollToEvent(String eventTitle);

    public abstract boolean checkCurrentDate(String date);
}
