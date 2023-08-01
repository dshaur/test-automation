package com.solvd.carina.demo.mobile.gui.pages.android.calendar;

import com.solvd.carina.demo.mobile.gui.pages.common.calendar.SearchPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SearchPageBase.class)
public class SearchPage extends SearchPageBase {

    @FindBy(xpath = "//android.widget.EditText[@text=\"Search\"]")
    private ExtendedWebElement searchBar;

    @FindBy(xpath = "//android.view.View[@content-desc=\"%s\"]/following-sibling::android.view.View[@content-desc=\"%s, All day: \"]")
    private ExtendedWebElement desiredEventResult;

    public SearchPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(searchBar);
    }

    @Override
    public void enterSearchText(String searchText) {

        // Type text
        searchBar.type(searchText);

        // Use Actions class to perform an 'ENTER' keyboard operation
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }

    @Override
    public void selectDesiredEvent(String year, String eventName) {
        desiredEventResult.format(year, eventName);
        desiredEventResult.click();
    }
}
