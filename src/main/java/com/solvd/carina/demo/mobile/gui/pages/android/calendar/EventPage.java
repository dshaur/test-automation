package com.solvd.carina.demo.mobile.gui.pages.android.calendar;

import com.solvd.carina.demo.mobile.gui.pages.common.calendar.EventPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = EventPageBase.class)
public class EventPage extends EventPageBase {

    @FindBy(id = "com.google.android.calendar:id/title")
    private ExtendedWebElement eventTitle;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"More options\"]")
    private ExtendedWebElement moreOptionsBtn;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Delete\"]")
    private ExtendedWebElement deleteBtn;
    @FindBy(id = "android:id/button1")
    private ExtendedWebElement confirmDeleteBtn;

    public EventPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return eventTitle.isElementPresent(3);
    }

    @Override
    public void deleteEvent() {
        moreOptionsBtn.click();
        deleteBtn.click();
        confirmDeleteBtn.click();
    }

    @Override
    public String getEventTitle() {
        return eventTitle.getText();
    }
}
