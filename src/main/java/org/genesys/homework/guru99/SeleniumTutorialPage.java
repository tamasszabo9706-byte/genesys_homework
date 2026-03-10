package org.genesys.homework.guru99;

import org.genesys.homework.UIPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * This class represents the selenium tutorial page
 * (<a href="https://www.guru99.com/selenium-tutorial.html">https://www.guru99.com/selenium-tutorial.html</a>)
 */
public class SeleniumTutorialPage extends UIPage {
    @FindBy(id = "wrapper")
    private WebElement wrapper;

    @FindBy(className = "kt-pane103540_d5f2da-a0")
    private List<WebElement> syllabusItems;

    @FindBy(id = "cb-215310")
    private WebElement buttonWrapper;

    @FindBy(css = "#cb-215310 button")
    private WebElement submitButton;

    public SeleniumTutorialPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    /**
     * Waits until the editor is loaded
     */
    public void isLoaded() {
        wrapper.isDisplayed();
    }

    /**
     * Scrolls to the place where the button should be displayed and waits until it appears
     */
    public void waitUntilButtonWrapperIsLoaded() {
        new Actions(driver).scrollToElement(syllabusItems.get(4)).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> buttonWrapper.isDisplayed());
    }
}
