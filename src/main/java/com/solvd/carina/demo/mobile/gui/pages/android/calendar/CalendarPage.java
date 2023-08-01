package com.solvd.carina.demo.mobile.gui.pages.android.calendar;

import com.solvd.carina.demo.mobile.gui.pages.common.calendar.CalendarPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CalendarPageBase.class)
public class CalendarPage extends CalendarPageBase implements IMobileUtils {

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Jump to Today\"]")
    private ExtendedWebElement jumpToTodayBtn;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Create new event and more\"]")
    private ExtendedWebElement createNewBtn;

    @FindBy(id = "com.google.android.calendar:id/speed_dial_event_container")
    private ExtendedWebElement createEventBtn;

    @FindBy(xpath = "//android.view.View[@content-desc=\"%s, All day: \"]")
    private ExtendedWebElement event;

    @FindBy(xpath = "//android.view.View[@content-desc=\"%s, All day: , %s\"]")
    private ExtendedWebElement coloredEvent;
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Search\"]")
    private ExtendedWebElement searchBtn;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Close\"]")
    private ExtendedWebElement cancelBtn;

    @FindBy(xpath = "//android.view.View[@content-desc=\"%s, Open Day View\"]")
    private ExtendedWebElement currentDate;

    public CalendarPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(jumpToTodayBtn);
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
    public boolean isEventPresent(String eventTitle) {
        return event.format(eventTitle).isElementPresent(3);
    }

    @Override
    public boolean isEventPresent(String eventTitle, String color) {
        return coloredEvent.format(eventTitle, color).isElementPresent(3);
    }

    @Override
    public void selectEvent(String eventTitle) {
        event.format(eventTitle).click();
    }

    @Override
    public void clickCancel() {
        cancelBtn.click();
    }

    @Override
    public void scrollToEvent(String eventTitle) {
        swipe(event.format(eventTitle));
        pause(5);
    }

    @Override
    public boolean checkCurrentDate(String date) {
        return currentDate.format(date).isElementPresent(3);
    }


}
