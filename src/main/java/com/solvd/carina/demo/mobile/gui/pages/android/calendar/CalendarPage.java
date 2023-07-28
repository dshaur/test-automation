package com.solvd.carina.demo.mobile.gui.pages.android.calendar;

import com.solvd.carina.demo.mobile.gui.pages.common.calendar.CalendarPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CalendarPageBase.class)
public class CalendarPage extends CalendarPageBase {

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Jump to Today\"]")
    private ExtendedWebElement jumpToTodayBtn;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Create new event and more\"]")
    private ExtendedWebElement createNewBtn;

    @FindBy(id = "com.google.android.calendar:id/speed_dial_event_container")
    private ExtendedWebElement createEventBtn;

    @FindBy(xpath = "//android.view.View[@content-desc=\"%s, All day: \"]")
    private ExtendedWebElement event;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Search\"]")
    private ExtendedWebElement searchBtn;

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void clickCreateEventBtn() {
        createEventBtn.click();
    }

    @Override
    public void clickJumpToTodayBtn() {
        jumpToTodayBtn.click();
    }

    @Override
    public void clickCreateNewBtn() {
        createNewBtn.click();
    }

    @Override
    public void clickSearchBtn() {
        searchBtn.click();
    }

    @Override
    public boolean isPageOpened() {
        return jumpToTodayBtn.isElementPresent(3);
    }

    public boolean isEventPresent(String eventTitle) {
        return event.format(eventTitle).isElementPresent(3);
    }

    public void selectEvent(String eventTitle) {
        event.format(eventTitle).click();
    }
}
