package com.solvd.carina.demo.mobile.gui.pages.android.calendar;

import com.solvd.carina.demo.mobile.gui.pages.common.calendar.EventCreationPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = EventCreationPageBase.class)
public class EventCreationPage extends EventCreationPageBase {
    @FindBy(id = "com.google.android.calendar:id/title")
    private ExtendedWebElement title;

    @FindBy(className = "android.widget.Switch")
    private ExtendedWebElement switchBtn;

    @FindBy(id = "com.google.android.calendar:id/save")
    private ExtendedWebElement saveBtn;

    @FindBy(xpath = "//android.widget.LinearLayout[@bounds='[0,1792][1080,1925]']")
    private ExtendedWebElement eventColorBtn;

    @FindBy(id = "com.google.android.calendar:id/description")
    private ExtendedWebElement eventDescription;

    @FindBy(xpath = "//android.widget.TextView[@text='%s']")
    private ExtendedWebElement eventColor;


    public EventCreationPage(WebDriver driver) {

        super(driver);
        setUiLoadedMarker(title);
    }

    @Override
    public String getEventTitle() {
        return title.getText();
    }

    @Override
    public String getEventColor() {
        return eventColor.getText();
    }

    @Override
    public String getEventDescription() {
        return eventDescription.getText();
    }

    @Override
    public void enterTitle(String eventTitle) {
        title.type(eventTitle);
    }

    @Override
    public void clickSwitchBtn() {
        switchBtn.click();
    }

    @Override
    public void clickEventColor() {
        eventColorBtn.click();
    }

    @Override
    public void selectDesiredColor(String color) {
        eventColor.format(color).click();
    }

    @Override
    public void changeEventDescription(String description) {
        eventDescription.click();
        eventDescription.type(description);
    }

    @Override
    public void clickSaveBtn() {
        saveBtn.click();
    }


}
