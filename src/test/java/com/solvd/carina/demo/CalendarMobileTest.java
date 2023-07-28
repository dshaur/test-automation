package com.solvd.carina.demo;

import com.solvd.carina.demo.mobile.gui.pages.android.calendar.CalendarPage;
import com.solvd.carina.demo.mobile.gui.pages.android.calendar.EventCreationPage;
import com.solvd.carina.demo.mobile.gui.pages.android.calendar.EventPage;
import com.solvd.carina.demo.mobile.gui.pages.android.calendar.SearchPage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarMobileTest implements IAbstractTest {

    // Test Case 1: Create a new event that lasts All day
    @Test
    public void eventCreationTest() {
        CalendarPage calendarPage = new CalendarPage(getDriver());
        // Check if the app is opened
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page isn't opened");

        // Click the create new button
        calendarPage.clickCreateNewBtn();

        // Choose the button to create a new event
        calendarPage.clickCreateEventBtn();

        EventCreationPage eventCreationPage = new EventCreationPage(getDriver());

        // Check if the Event Creation page is opened
        Assert.assertTrue(eventCreationPage.isPageOpened(), "Calendar page isn't opened");

        // Enter event details
        String eventTitle = "My Birthday";

        eventCreationPage.enterTitle(eventTitle); // Enter event title
        eventCreationPage.clickSwitchBtn(); // Choose "All day" for the time by enabling switch button
        eventCreationPage.clickSaveBtn(); // Click "Save"

        // Check if the event was created
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page isn't opened");
        Assert.assertTrue(calendarPage.isEventPresent(eventTitle), "Event isn't present");
    }

    // Test Case 2: Create a new event that lasts All day and try to delete it
    @Test
    public void deleteEventTest() {
        CalendarPage calendarPage = new CalendarPage(getDriver());
        // Check if the app is opened
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page isn't opened");

        // Click the create new button ('+')
        calendarPage.clickCreateNewBtn();

        // Choose the button to create a new event
        calendarPage.clickCreateEventBtn();

        EventCreationPage eventCreationPage = new EventCreationPage(getDriver());

        // Check if the Event Creation page is opened
        Assert.assertTrue(eventCreationPage.isPageOpened(), "Calendar page isn't opened");

        // Enter event details
        String eventTitle = "Delete Me";

        eventCreationPage.enterTitle(eventTitle); // Enter event title
        eventCreationPage.clickSwitchBtn(); // Choose "All day" for the time by enabling switch button
        eventCreationPage.clickSaveBtn(); // Click "Save"

        // Check if the event was created
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page isn't opened");
        Assert.assertTrue(calendarPage.isEventPresent(eventTitle), "Event isn't present");

        // Select the event from the calendar
        calendarPage.selectEvent(eventTitle);

        // Check if the event was opened
        EventPage eventPage = new EventPage(getDriver());
        Assert.assertTrue(eventPage.isPageOpened(), "Event was not opened");

        // Delete the event
        eventPage.deleteEvent();

        // Verify if the event was deleted
        Assert.assertFalse(calendarPage.isEventPresent(eventTitle), "Event was not deleted");
    }

    // Test Case 3: Perform a search for a specific event and select from results
    @Test
    public void searchEventsTest() {
        CalendarPage calendarPage = new CalendarPage(getDriver());
        // Check if the app is opened
        Assert.assertTrue(calendarPage.isPageOpened(), "Calendar page isn't opened");

        // Click the search button to start searching for events
        calendarPage.clickSearchBtn();

        // Check if the search page is opened
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.isPageOpened(), "Search page isn't opened");

        // Search and select a desired event
        String eventName = "Independence Day";
        String year = "2023";
        searchPage.enterSearchText(eventName);
        searchPage.selectDesiredEvent(year, eventName);

        // Check if the event was selected
        EventPage eventPage = new EventPage(getDriver());
        Assert.assertTrue(eventPage.isPageOpened(), "Event was not opened");
        Assert.assertEquals(eventPage.getEventTitle(), eventName, "Event was not selected");

    }
}
