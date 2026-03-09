package org.genesys.homework.guru99;

import org.genesys.homework.UIPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * This class represents the live selenium project page
 * (<a href="https://www.guru99.com/live-selenium-project.html">https://www.guru99.com/live-selenium-project.html</a>)
 */
public class LiveSeleniumProjectPage extends UIPage {
    @FindBy(id = "wrapper")
    private WebElement wrapper;

    public LiveSeleniumProjectPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Waits until the page is loaded
     */
    public void isLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> wrapper.isDisplayed());
    }
}
