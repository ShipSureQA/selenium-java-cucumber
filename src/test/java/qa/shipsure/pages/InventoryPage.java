package qa.shipsure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage {
    private static final By TITLE = dataTest("title");
    private static final By ITEMS = dataTest("inventory-item");
    private static final By ITEM_NAMES = dataTest("inventory-item-name");
    private static final By ITEM_PRICES = dataTest("inventory-item-price");
    private static final By SORT = dataTest("product-sort-container");
    private static final By CART_BADGE = dataTest("shopping-cart-badge");
    private static final By CART_LINK = dataTest("shopping-cart-link");

    public InventoryPage(WebDriver driver) { super(driver); }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("inventory.html"))
                && "Products".equals(text(TITLE));
    }

    /** "Sauce Labs Backpack" -> "sauce-labs-backpack" (matches the site's data-test ids). */
    private static String slug(String productName) {
        return productName.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
    }

    public void addToCart(String productName) {
        click(dataTest("add-to-cart-" + slug(productName)));
    }

    public void removeFromCart(String productName) {
        click(dataTest("remove-" + slug(productName)));
    }

    public int productCount() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ITEMS));
        return driver.findElements(ITEMS).size();
    }

    /** Visible option text, e.g. "Price (low to high)". */
    public void sortBy(String optionText) {
        new Select(visible(SORT)).selectByVisibleText(optionText);
    }

    public List<String> productNames() {
        return texts(ITEM_NAMES);
    }

    public List<Double> productPrices() {
        return texts(ITEM_PRICES).stream().map(p -> Double.parseDouble(p.replace("$", ""))).toList();
    }

    /** 0 when the badge is hidden (empty cart). */
    public int cartCount() {
        return isPresent(CART_BADGE) ? Integer.parseInt(text(CART_BADGE)) : 0;
    }

    public void openCart() {
        click(CART_LINK);
    }
}
