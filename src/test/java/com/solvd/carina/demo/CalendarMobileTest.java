package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.common.calendar.CalendarPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.calendar.EventCreationPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.calendar.EventPageBase;
import com.solvd.carina.demo.mobile.gui.pages.common.calendar.SearchPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.dataprovider.annotations.XlsDataSourceParameters;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CalendarMobileTest implements IAbstractTest, IAbstractDataProvider {

    // Test Case 1: Create a new event that lasts All day
    @Test
    public void eventCreationTest() {
        CalendarPageBase calendarPage = initPage(getDriver(), CalendarPageBase.class);
        // Check if the app is opened
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page is not opened");

        // Click the create new button
        calendarPage.clickCreateNewBtn();

        // Choose the button to create a new event
        calendarPage.clickCreateEventBtn();

        EventCreationPageBase eventCreationPage = initPage(getDriver(), EventCreationPageBase.class);

        // Check if the Event Creation page is opened
        Assert.assertTrue(eventCreationPage.isPageOpened(), "Calendar page is not opened");

        // Enter event details
        String eventTitle = "My Birthday";

        eventCreationPage.enterTitle(eventTitle); // Enter event title
        eventCreationPage.clickSwitchBtn(); // Choose "All day" for the time by enabling switch button
        eventCreationPage.clickSaveBtn(); // Click "Save"

        // Check if the event was created
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page is not opened");
        Assert.assertTrue(calendarPage.isEventPresent(eventTitle), "Event is not present");
    }

    // Test Case 2: Create a new event that lasts All day and try to delete it
    @Test
    public void deleteEventTest() {
        CalendarPageBase calendarPage = initPage(getDriver(), CalendarPageBase.class);
        // Check if the app is opened
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page is not opened");

        // Click the create new button ('+')
        calendarPage.clickCreateNewBtn();

        // Choose the button to create a new event
        calendarPage.clickCreateEventBtn();

        EventCreationPageBase eventCreationPage = initPage(getDriver(), EventCreationPageBase.class);

        // Check if the Event Creation page is opened
        Assert.assertTrue(eventCreationPage.isPageOpened(), "Calendar page is not opened");

        // Enter event details
        String eventTitle = "Delete Me";

        eventCreationPage.enterTitle(eventTitle); // Enter event title
        eventCreationPage.clickSwitchBtn(); // Choose "All day" for the time by enabling switch button
        eventCreationPage.clickSaveBtn(); // Click "Save"

        // Check if the event was created
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page is not opened");
        Assert.assertTrue(calendarPage.isEventPresent(eventTitle), "Event is not present");

        // Select the event from the calendar
        calendarPage.selectEvent(eventTitle);

        // Check if the event was opened
        EventPageBase eventPage = initPage(getDriver(), EventPageBase.class);
        Assert.assertTrue(eventPage.isPageOpened(), "Event was not opened");

        // Delete the event
        eventPage.deleteEvent();

        // Verify if the event was deleted
        Assert.assertFalse(calendarPage.isEventPresent(eventTitle), "Event was not deleted");
    }

    // Test Case 3: Perform a search for a specific event and select from results
    @Test(dataProvider = "DataProvider")
    @XlsDataSourceParameters(path = "data_source/calendarMobile.xlsx", sheet = "HolidaySearches", dsUid = "TUID", dsArgs = "eventName, year")
    public void searchEventsTest(String eventName, String year) {
        CalendarPageBase calendarPage = initPage(getDriver(), CalendarPageBase.class);
        // Check if the app is opened
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page is not opened");

        // Click the search button to start searching for events
        calendarPage.clickSearchBtn();

        // Check if the search page is opened
        SearchPageBase searchPage = initPage(getDriver(), SearchPageBase.class);
        Assert.assertTrue(searchPage.isPageOpened(), "Search page is not opened");

        // Search and select a desired event
        searchPage.enterSearchText(eventName);
        searchPage.selectDesiredEvent(year, eventName);

        // Check if the event was selected
        EventPageBase eventPage = initPage(getDriver(), EventPageBase.class);
        Assert.assertTrue(eventPage.isPageOpened(), "Event was not opened");
        Assert.assertEquals(eventPage.getEventTitle(), eventName, "Event was not selected");

    }

    // Test Case 4: Perform event details editing
    @Test
    public void editEventTest() {
        CalendarPageBase calendarPage = initPage(getDriver(), CalendarPageBase.class);
        // Check if the app is opened
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page is not opened");

        // Click the create new button
        calendarPage.clickCreateNewBtn();

        // Choose the button to create a new event
        calendarPage.clickCreateEventBtn();

        EventCreationPageBase eventCreationPage = initPage(getDriver(), EventCreationPageBase.class);

        // Check if the Event Creation page is opened
        Assert.assertTrue(eventCreationPage.isPageOpened(), "Event Creation page is not opened");

        // Enter event details
        String eventTitle = "Edit me";

        eventCreationPage.enterTitle(eventTitle); // Enter event title
        eventCreationPage.clickSwitchBtn(); // Choose "All day" for the time by enabling switch button
        eventCreationPage.clickSaveBtn(); // Click "Save"

        // Check if the event was created
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page isn't opened");
        Assert.assertTrue(calendarPage.isEventPresent(eventTitle), "Event is not present");

        // Select the existing (previously created) event from the calendar
        calendarPage.selectEvent(eventTitle);

        // Check if the event page was opened
        EventPageBase eventPage = initPage(getDriver(), EventPageBase.class);
        Assert.assertTrue(eventPage.isPageOpened(), "Event page was not opened");

        // Start the event editing
        eventPage.clickEditBtn();

        // Verify if the event creation page is opened
        Assert.assertTrue(eventCreationPage.isPageOpened(), "Event Creation page isn't opened");

        // Modify the event details
        String newEventTitle = "My Birthday";
        String color = "Sage";
        String description = "This is my 29th birthday";
        eventCreationPage.enterTitle(newEventTitle); // Change event title
        eventCreationPage.clickEventColor(); // Click the event color option
        eventCreationPage.selectDesiredColor(color); // Select the desired color
        eventCreationPage.changeEventDescription(description); // Change the event description

        // Soft assert the changes
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(eventCreationPage.getEventTitle(), newEventTitle, "Title was not changed");
        softAssert.assertEquals(eventCreationPage.getEventColor(), color, "Color was not changed");
        softAssert.assertEquals(eventCreationPage.getEventDescription(), description, "Description was not changed");

        eventCreationPage.clickSaveBtn(); // Save changes

        // Check if the edited event is present
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page isn't opened");
        Assert.assertTrue(calendarPage.isEventPresent(newEventTitle, color), "Event isn't present");
        softAssert.assertAll();
    }

    // Test 5: Perform calendar scrolling and click Jump to today button
    @Test
    public void jumpToTodayTest() {
        CalendarPageBase calendarPage = initPage(getDriver(), CalendarPageBase.class);

        // Check if the app is opened
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page isn't opened");

        // Create a custom format for the current date, so it matches the one in the calendar
        LocalDate currentDate = LocalDate.now(); // Get the current date
        String customCurrentDate = currentDate.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.ENGLISH));

        // Scroll to a certain event
        String eventName = "Halloween";
        calendarPage.scrollToEvent(eventName);
        calendarPage.selectEvent(eventName); // Check the event details
        // Check if the event page was opened and the selected event is present
        EventPageBase eventPage = initPage(getDriver(), EventPageBase.class);
        Assert.assertTrue(eventPage.isPageOpened(), "Event was not opened");
        Assert.assertEquals(eventPage.getEventTitle(), eventName, "Event was not selected");

        calendarPage.clickCancel(); // Click "Cancel" button to go back to the calendar

        // Use the Jump To Today's date button
        calendarPage.clickJumpToTodayBtn();

        // Check if the current date is present
        Assert.assertTrue(calendarPage.checkCurrentDate(customCurrentDate), "Current date not present or mismatch");

    }
}
