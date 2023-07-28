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


    public EventCreationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return title.isElementPresent(3);
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
    public void clickSaveBtn() {
        saveBtn.click();
    }


}
