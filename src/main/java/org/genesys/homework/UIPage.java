package org.genesys.homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base class for pages
 */
public abstract class UIPage {
    protected WebDriver driver;
    protected String windowHandle;

    public UIPage(WebDriver driver) {
        this.driver = driver;
        windowHandle = driver.getWindowHandle();
        PageFactory.initElements(driver, this);
    }

    public String getWindowHandle() {
        return windowHandle;
    }
}
