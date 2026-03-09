package org.genesys.homework.guru99;

import org.genesys.homework.UIPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

/**
 * This class represents the home page of the guru99 demo site
 * (<a href="https://demo.guru99.com/test/guru99home/">https://demo.guru99.com/test/guru99home/</a>)
 */
public class GuruHomePage extends UIPage {
    @FindBy(id = "a077aa5e")
    private WebElement iframeSeleniumLive;

    @FindBy(tagName = "img")
    private WebElement imageInTheIFrame;

    @FindBy(className = "item118")
    private WebElement testingMenuItem;

    @FindBy(className = "item121")
    private WebElement seleniumMenuItem;

    public GuruHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks on the image of the iframe and switches to the windowHandle of the newly opened tab
     */
    public void clickIframeImage() {
        driver.switchTo().frame(iframeSeleniumLive);
        imageInTheIFrame.click();
        driver.switchTo().defaultContent();
        driver.switchTo().window(getNewWindowHandle());
    }

    /**
     * Hovers the cursor over the testing item to display a dropdown menu
     */
    public void hoverOverTestingMenuItem() {
        new Actions(driver).moveToElement(testingMenuItem).perform();
    }

    /**
     * Clicks on selenium item which navigates to another page. Only works if the dropdown was opened via {@link #hoverOverTestingMenuItem()}
     */
    public void clickSeleniumMenuItem() {
        seleniumMenuItem.click();
    }

    /**
     * Checks the windowHandles to find one which is not the current one.
     *
     * @return new windows handle
     */
    private String getNewWindowHandle() {
        return driver.getWindowHandles().stream().filter(h -> !h.equals(driver.getWindowHandle())).findFirst()
                .orElseThrow(() -> new NoSuchElementException("No secondary window handle found;"));
    }
}
