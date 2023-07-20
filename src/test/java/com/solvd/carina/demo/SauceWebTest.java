package com.solvd.carina.demo;

import com.solvd.carina.demo.gui.pages.desktop.sauce.LoginPage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SauceWebTest implements IAbstractTest {

    @DataProvider(name = "loginCredentials")
    public Object[][] provideLoginCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "loginCredentials")
    public void loginTest(String username, String password) {
        // Initialize the loginPage object
        LoginPage loginPage = new LoginPage(getDriver());

        // Open the login page
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform the login using provided credentials
        loginPage.login(username, password);

        // Validate whether login was successful or not
        SoftAssert softAssert = new SoftAssert();
        if (loginPage.isLoginErrorMessagePresent()) {
            // Login failed
            String errorMessage = loginPage.getLoginErrorMessage();
            softAssert.fail("Login failed for user: " + username + ". Error message: " + errorMessage);
        } else {
            // Login successful

            // Verify that the user is redirected to the Home/InventoryPage
            // Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened");
        }

        softAssert.assertAll();
    }
}
