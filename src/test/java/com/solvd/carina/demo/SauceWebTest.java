package com.solvd.carina.demo;

import com.solvd.carina.demo.gui.pages.desktop.sauce.LoginPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SauceWebTest implements IAbstractTest {

    @DataProvider(name = "loginCredentials")
    public Object[][] provideLoginCredentials() {
        return new Object[][]{
                {R.TESTDATA.get("user1.name"), R.TESTDATA.get("user1.pwd"), true},
                {R.TESTDATA.get("user2.name"), R.TESTDATA.get("user2.pwd"), false},
                {R.TESTDATA.get("user3.name"), R.TESTDATA.get("user3.pwd"), true},
                {R.TESTDATA.get("user4.name"), R.TESTDATA.get("user4.pwd"), true}
        };
    }

    @Test(dataProvider = "loginCredentials")
    public void loginTest(String username, String password, boolean shouldLogin) {
        // Initialize the loginPage object
        LoginPage loginPage = new LoginPage(getDriver());

        // Open the login page
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform the login using provided credentials
        loginPage.login(username, password);

        // Validate whether login was successful or not
        Assert.assertEquals(!loginPage.isLoginErrorMessagePresent(), shouldLogin, "Login failed");

    }
}
