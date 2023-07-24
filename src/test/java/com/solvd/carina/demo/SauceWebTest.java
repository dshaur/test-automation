package com.solvd.carina.demo;

import com.solvd.carina.demo.gui.pages.desktop.sauce.*;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import com.zebrunner.carina.dataprovider.annotations.XlsDataSourceParameters;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceWebTest implements IAbstractTest, IAbstractDataProvider {

    // Test Case 1
    @Test(dataProvider = "DataProvider")
    @XlsDataSourceParameters(path = "data_source/sauceWeb.xlsx", sheet = "Credentials", dsUid = "TUID", dsArgs = "username, password, shouldLogin")
    public void loginTest(String username, String password, String shouldLogin) {
        // Initialize the loginPage object
        LoginPage loginPage = new LoginPage(getDriver());

        // Open the login page
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform the login using provided credentials
        loginPage.login(username, password);

        // Validate whether login was successful or not
        Assert.assertEquals(!loginPage.isLoginErrorMessagePresent((long) 3), Boolean.valueOf(shouldLogin), "Login failed");

    }

    // Test Case 2
    @Test
    public void addToCartTest() {
        // Initialize the loginPage object
        LoginPage loginPage = new LoginPage(getDriver());

        // Open the login page
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform the login using user1 credentials form testdata config file (since we're not using the DataProvider for this test)
        String username = R.TESTDATA.get("user1.name");
        String password = R.TESTDATA.get("user1.pwd");
        loginPage.login(username, password);

        // Validate that login is successful and the InventoryPage (which is the homepage) is displayed
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened");

        // Add a product to the shopping cart from the list
        String productName = "Sauce Labs Backpack"; // Can be replaced with the actual product name you want to add
        inventoryPage.addToCart(productName);
        inventoryPage.clickCartIcon();

        // Verify that the product is added successfully to the ShoppingCartPage
        ShoppingCartPage cartPage = new ShoppingCartPage(getDriver());
        Assert.assertTrue(cartPage.isPageOpened(), "Shopping Cart page is not opened");
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product is not added to the cart");
    }

    // Test Case 3
    @Test
    public void removeFromCartTest() {
        // Initialize the loginPage object
        LoginPage loginPage = new LoginPage(getDriver());

        // Open the login page
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform the login using user1 credentials from testdata config file (since we're not using the DataProvider for this test)
        String username = R.TESTDATA.get("user1.name");
        String password = R.TESTDATA.get("user1.pwd");
        loginPage.login(username, password);

        // Validate that login is successful and the InventoryPage (which is the homepage) is displayed
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened");

        // Add two products to the shopping cart from the list
        String productName1 = "Sauce Labs Backpack"; // Replace this with the actual product name you want to add
        String productName2 = "Sauce Labs Bolt T-Shirt"; // Replace this with the actual product name you want to add
        inventoryPage.addToCart(productName1);
        inventoryPage.addToCart(productName2);
        inventoryPage.clickCartIcon();

        // Verify that both products are added successfully to the ShoppingCartPage
        ShoppingCartPage cartPage = new ShoppingCartPage(getDriver());
        Assert.assertTrue(cartPage.isPageOpened(), "Shopping Cart page is not opened");
        Assert.assertTrue(cartPage.isProductInCart(productName1), "Product 1 is not added to the cart");
        Assert.assertTrue(cartPage.isProductInCart(productName2), "Product 2 is not added to the cart");

        // Remove the first product from the cart
        cartPage.removeProductFromCart(productName1);

        // Verify that the first product is removed successfully from the ShoppingCartPage
        Assert.assertFalse(cartPage.isProductInCart(productName1), "Product 1 is not removed from the cart");
        // Verify that the second product is still in the cart
        Assert.assertTrue(cartPage.isProductInCart(productName2), "Product 2 is removed from the cart");
    }

    // Test Case 4
    @Test
    public void cartCheckoutTest() {
        // Initialize the loginPage object
        LoginPage loginPage = new LoginPage(getDriver());

        // Open the login page
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform the login using user1 credentials (since we're not using the DataProvider for this test)
        String username = R.TESTDATA.get("user1.name");
        String password = R.TESTDATA.get("user1.pwd");
        loginPage.login(username, password);

        // Validate that login is successful and the InventoryPage (which is also the homepage) is displayed
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened");

        // Add multiple products to the shopping cart from the list
        String productName1 = "Sauce Labs Backpack"; // Replace this with the actual product name you want to add
        String productName2 = "Sauce Labs Bolt T-Shirt"; // Replace this with the actual product name you want to add
        inventoryPage.addToCart(productName1);
        inventoryPage.addToCart(productName2);
        inventoryPage.clickCartIcon();

        // Verify that both products are added successfully to the ShoppingCartPage
        ShoppingCartPage cartPage = new ShoppingCartPage(getDriver());
        Assert.assertTrue(cartPage.isPageOpened(), "Shopping Cart page is not opened");
        Assert.assertTrue(cartPage.isProductInCart(productName1), "Product 1 is not added to the cart");
        Assert.assertTrue(cartPage.isProductInCart(productName2), "Product 2 is not added to the cart");

        // Go to the CheckoutFormPage to start the checkout process
        cartPage.clickCheckoutButton();
        CheckOutFormPage checkoutFormPage = new CheckOutFormPage(getDriver());
        Assert.assertTrue(checkoutFormPage.isPageOpened(), "Checkout page is not opened");

        // Complete the checkout form with valid information
        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "12345";
        checkoutFormPage.fillCheckoutForm(firstName, lastName, postalCode);

        // Go to the CheckoutOverviewPage and verify the checkout details
        checkoutFormPage.clickContinueButton();
        CheckOutOverviewPage checkoutOverviewPage = new CheckOutOverviewPage(getDriver());
        Assert.assertTrue(checkoutOverviewPage.isPageOpened(), "Checkout Overview page is not opened");
        Assert.assertTrue(checkoutOverviewPage.isProductInCart(productName1), "Product 1 is not in the cart");
        Assert.assertTrue(checkoutOverviewPage.isProductInCart(productName2), "Product 2 is not in the cart");

        // Finalize the checkout and verify the CheckoutCompletePage is displayed
        checkoutOverviewPage.clickFinishButton();
        CheckOutCompletePage checkoutCompletePage = new CheckOutCompletePage(getDriver());
        Assert.assertTrue(checkoutCompletePage.isPageOpened(), "Checkout Complete page is not opened");

        // Go back to homepage and verify the InventoryPage is displayed
        checkoutCompletePage.clickBackHomeButton();
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened");
    }

    // Test Case 5
    @Test
    public void logoutTest() {
        // Initialize the loginPage object
        LoginPage loginPage = new LoginPage(getDriver());

        // Open the login page
        loginPage.open();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is not opened");

        // Perform the login using user1 credentials (since we're not using the DataProvider for this test)
        String username = R.TESTDATA.get("user1.name");
        String password = R.TESTDATA.get("user1.pwd");
        loginPage.login(username, password);

        // Validate that login is successful and the InventoryPage (which is also the homepage) is displayed
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page is not opened");

        // Open the menu and click on the logout link
        inventoryPage.getTopMenu().logout();

        // Validate that the LoginPage is displayed as a result of successful logout
        Assert.assertTrue(loginPage.isPageOpened(), "Logout failed. Login page is not displayed.");
    }
}

