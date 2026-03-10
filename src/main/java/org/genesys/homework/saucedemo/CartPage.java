package org.genesys.homework.saucedemo;

import org.genesys.homework.UIPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the cart page
 * (<a href="https://www.saucedemo.com/cart.html">https://www.saucedemo.com/cart.html</a>)
 */
public class CartPage extends UIPage {
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckout() {
        checkoutButton.click();
    }

    public void fillFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void fillPostalCode(String postalCode) {
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void clickFinish() {
        finishButton.click();
    }
}
