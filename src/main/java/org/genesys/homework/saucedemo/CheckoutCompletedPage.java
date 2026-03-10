package org.genesys.homework.saucedemo;

import org.genesys.homework.UIPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represents the welcome page which shown after a successful checkout
 * (<a href="https://www.saucedemo.com/checkout-complete.html">https://www.saucedemo.com/checkout-complete.html</a>)
 */
public class CheckoutCompletedPage extends UIPage {
    @FindBy(className = "complete-header")
    private WebElement thankYouMessage;

    public CheckoutCompletedPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getThankYouMessage() {
        return thankYouMessage;
    }
}
