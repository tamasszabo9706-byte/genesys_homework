package org.genesys.homework;

import org.genesys.homework.guru99.GuruHomePage;
import org.genesys.homework.guru99.LiveSeleniumProjectPage;
import org.genesys.homework.guru99.SeleniumTutorialPage;
import org.genesys.homework.onlinehtmleditor.OnlineHTMLEditorPage;
import org.genesys.homework.saucedemo.CartPage;
import org.genesys.homework.saucedemo.CheckoutCompletedPage;
import org.genesys.homework.saucedemo.InventoryPage;
import org.genesys.homework.saucedemo.SwagLabsLoginPage;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains UI tests.
 */
@Execution(ExecutionMode.CONCURRENT)
public class UITest {
    private WebDriver driver;

    @BeforeEach
    public void beforeEach() {
        // Turn off popup about weak password
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new LinkedHashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    /**
     * Test case about the purchase process (login, adding items to cart, checkout etc.)
     */
    @Test
    public void purchaseProcessWorksProperly() throws FileNotFoundException {
        // Read credentials
        JSONObject credentials = new JSONObject(readResource("credential.json"));

        // Open URL
        driver.get("https://www.saucedemo.com/inventory.html");

        // Login
        SwagLabsLoginPage loginPage = new SwagLabsLoginPage(driver);
        loginPage.login(credentials.get("username").toString(), credentials.get("password").toString());

        // Add items
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickAddBackPackToCartButton();
        inventoryPage.clickAddJacketToCartButton();

        assertEquals(2, inventoryPage.getNumberOfAddedItems(), "The number on the cart symbol was not correct!");

        // Go through the checkout process
        inventoryPage.clickShoppingCartButton();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        cartPage.fillFirstName("testFirst");
        cartPage.fillLastName("testLast");
        cartPage.fillPostalCode("testPostalCode");
        cartPage.clickContinue();
        cartPage.clickFinish();

        // Validate the thank you message
        CheckoutCompletedPage checkoutCompletedPage = new CheckoutCompletedPage(driver);
        assertAll(
                () -> assertTrue(checkoutCompletedPage.getThankYouMessage().isDisplayed(), "Thank you message is not displayed!"),
                () -> assertEquals("Thank you for your order!", checkoutCompletedPage.getThankYouMessage().getText()));
    }

    /**
     * Test case about error message, scrolling and footer
     */
    @Test
    public void errorMessageAndFooterAreDisplayedCorrectly() {
        // Open url
        driver.get("https://www.saucedemo.com/inventory.html");

        // Click login button
        SwagLabsLoginPage loginPage = new SwagLabsLoginPage(driver);
        loginPage.clickLoginButton();

        assertEquals("Epic sadface: Username is required", loginPage.getErrorMessage(), "The error message was not correct");

        // Login with valid username and password
        loginPage.login("standard_user", "secret_sauce");

        // Scroll to footer
        InventoryPage inventoryPage = new InventoryPage(driver);
        Actions actions = new Actions(driver);
        actions.scrollToElement(inventoryPage.getFooterElement()).perform();

        assertAll(
                () -> assertTrue(inventoryPage.getFooterElement().getText().contains("2026"), "Footer did not contain \"2026\""),
                () -> assertTrue(inventoryPage.getFooterElement().getText().contains("Terms of Service"), "Footer did not contain \"Terms of Service\"")
        );
    }

    /**
     * Test case about the testing of a rich text editor
     */
    @Test
    public void richTextEditorWorksProperly() {
        // Open url
        driver.get("https://onlinehtmleditor.dev/");

        // Wait until page is loaded
        OnlineHTMLEditorPage page = new OnlineHTMLEditorPage(driver);
        page.isLoaded();

        // Type Automation(bold) Test(underlined) Example
        page.switchBoldFormat();
        page.typeText("Automation");
        page.switchBoldFormat();
        page.typeText(" ");
        page.switchUnderlineFormat();
        page.typeText("Test");
        page.switchUnderlineFormat();
        page.typeText(" Example");

        assertAll(
                () -> assertEquals(1, page.getBoldElements().size(), "There should be only 1 bold element"),
                () -> assertEquals("Automation", page.getBoldElements().get(0).getText(), "Text of the bold element was not correct"),
                () -> assertEquals(1, page.getUnderlinedElements().size(), "There should be only 1 underlined element"),
                () -> assertEquals("Test", page.getUnderlinedElements().get(0).getText(), "Text of the bold element was not correct"),
                () -> assertEquals("Automation Test Example", page.getTextOfEditorWithoutNoBreakChars(), "The text in the editor was not correct")
        );
    }

    /**
     * Test case about handling of iframes and tabs
     */
    @Test
    public void iFrameAndTabHandlingWorksProperly() {
        // Open url
        driver.get("https://demo.guru99.com/test/guru99home/");

        // Click on image in iframe
        GuruHomePage homePage = new GuruHomePage(driver);
        homePage.clickIframeImage();

        // Wait until the page is loaded
        LiveSeleniumProjectPage projectPage = new LiveSeleniumProjectPage(driver);
        projectPage.isLoaded();

        assertEquals("Selenium Live Project for Practice", driver.getTitle(), "The title of the page was not correct");

        // Close the tab
        driver.close();
        driver.switchTo().window(homePage.getWindowHandle());

        // Open selenium tutorial page
        homePage.hoverOverTestingMenuItem();
        homePage.clickSeleniumMenuItem();

        // Wait until the red submit button is loaded
        SeleniumTutorialPage tutorialPage = new SeleniumTutorialPage(driver);
        tutorialPage.isLoaded();
        tutorialPage.waitUntilButtonWrapperIsLoaded();

        assertAll(
                () -> assertTrue(tutorialPage.getSubmitButton().isDisplayed(), "Submit button was not displayed"),
                () -> assertEquals("Submit", tutorialPage.getSubmitButton().getText())
        );
    }

    /**
     * Reads file from resources folder
     *
     * @param fileName name of file
     * @return file as a string
     * @throws FileNotFoundException if the given file cannot be found in the folder
     */
    private static String readResource(String fileName) throws FileNotFoundException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                // Reading and printing the content of the file
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                // Handle IOException
                e.printStackTrace();
            } finally {
                try {
                    // Closing the inputStream after successful
                    // reading and printing of the file
                    inputStream.close();
                } catch (IOException e) {
                    // Handle IOException during closing
                    e.printStackTrace();
                }
            }
        } else {
            // File not found in the classpath
            throw new FileNotFoundException("File not found: " + fileName);
        }

        return stringBuilder.toString();
    }
}

