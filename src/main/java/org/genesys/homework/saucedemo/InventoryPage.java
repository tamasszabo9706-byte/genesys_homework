package org.genesys.homework.saucedemo;

import org.genesys.homework.UIPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the inventory page (inventory.html)
 */
public class InventoryPage extends UIPage {
    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartElement;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackPackToCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement addJacketToCartButton;

    @FindBy(tagName = "footer")
    private WebElement footerElement;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Adds backpack to the cart
     */
    public void clickAddBackPackToCartButton() {
        addBackPackToCartButton.click();
    }

    /**
     * Adds jacket to the cart
     */
    public void clickAddJacketToCartButton() {
        addJacketToCartButton.click();
    }

    /**
     * @return the number of items in the cart
     */
    public int getNumberOfAddedItems() {
        return Integer.parseInt(shoppingCartElement.getText());
    }

    public void clickShoppingCartButton() {
        shoppingCartElement.click();
    }

    public WebElement getFooterElement() {
        return footerElement;
    }

}
