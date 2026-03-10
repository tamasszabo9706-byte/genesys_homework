package org.genesys.homework.onlinehtmleditor;

import org.genesys.homework.UIPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * This class represents the online HTML editor page
 * (<a href="https://onlinehtmleditor.dev/">https://onlinehtmleditor.dev/</a>)
 */
public class OnlineHTMLEditorPage extends UIPage {
    @FindBy(className = "ck-content")
    WebElement richTextEditor;

    @FindBy(css = ".ck-content strong")
    List<WebElement> boldElements;

    @FindBy(css = ".ck-content u")
    List<WebElement> underlinedElements;

    @FindBy(css = ".ck-content > p")
    List<WebElement> elements;

    public OnlineHTMLEditorPage(WebDriver driver) {
        super(driver);
    }

    /**
     *
     * @param text
     */
    public void typeText(String text) {
        richTextEditor.sendKeys(text);
    }

    /**
     * Turns on (or off) bold format via keyboard shortcut
     */
    public void switchBoldFormat() {
        new Actions(driver).click(richTextEditor).keyDown(Keys.LEFT_CONTROL).sendKeys("b").keyUp(Keys.LEFT_CONTROL).perform();
    }

    /**
     * Turns on (or off) underline format via keyboard shortcut
     */
    public void switchUnderlineFormat() {
        Actions actions = new Actions(driver);
        actions.click(richTextEditor).keyDown(Keys.LEFT_CONTROL).sendKeys("u").keyUp(Keys.LEFT_CONTROL).perform();
    }

    public WebElement getRichTextEditor() {
        return richTextEditor;
    }

    public List<WebElement> getBoldElements() {
        return boldElements;
    }

    public List<WebElement> getUnderlinedElements() {
        return underlinedElements;
    }

    /**
     * @return text in the editor without the &NoBreak/WJ characters
     */
    public String getTextOfEditorWithoutNoBreakChars() {
        return richTextEditor.getText().replace("\u2060", "");
    }

    /**
     * Waits until the editor is loaded
     */
    public void isLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> richTextEditor.isDisplayed());
    }
}
