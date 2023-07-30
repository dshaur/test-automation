package com.solvd.carina.demo.mobile.gui.pages.android.calendar;

import com.solvd.carina.demo.mobile.gui.pages.common.calendar.EventPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
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
    @FindBy(id = "com.google.android.calendar:id/info_action_edit_hit")
    private ExtendedWebElement editBtn;

    public EventPage(WebDriver driver) {

        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(eventTitle);
    }

    @Override
    public void deleteEvent() {
        moreOptionsBtn.click();
        deleteBtn.click();
        confirmDeleteBtn.click();
    }

    @Override
    public void clickEditBtn() {
        editBtn.click();
    }

    @Override
    public String getEventTitle() {
        return eventTitle.getText();
    }
}
