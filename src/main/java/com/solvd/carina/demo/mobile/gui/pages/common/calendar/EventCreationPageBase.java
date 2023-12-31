package com.solvd.carina.demo.mobile.gui.pages.common.calendar;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class EventCreationPageBase extends AbstractPage {
    protected EventCreationPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String getEventTitle();

    public abstract String getEventColor();

    public abstract String getEventDescription();

    public abstract void enterTitle(String eventTitle);

    public abstract void clickSwitchBtn();

    public abstract void clickEventColor();

    public abstract void selectDesiredColor(String color);

    public abstract void changeEventDescription(String description);

    public abstract void clickSaveBtn();

}
