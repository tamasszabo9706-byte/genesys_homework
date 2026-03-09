package org.genesys.homework.saucedemo;

import org.genesys.homework.UIPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the login page of Swag Labs
 */
public class SwagLabsLoginPage extends UIPage {
    @FindBy(id = "root")
    private WebElement root;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    public SwagLabsLoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Performs a login with the given username and password
     */
    public void login(String username, String password) {
        fillUserName(username);
        fillPassword(password);
        loginButton.click();
    }

    public void fillUserName(String username) {
        usernameField.sendKeys(username);
    }

    public void fillPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
