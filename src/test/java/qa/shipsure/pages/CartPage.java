package qa.shipsure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
    private static final By ITEM_NAMES = dataTest("inventory-item-name");
    private static final By CHECKOUT = dataTest("checkout");

    public CartPage(WebDriver driver) { super(driver); }

    public List<String> itemNames() {
        wait.until(ExpectedConditions.urlContains("cart.html"));
        return texts(ITEM_NAMES);
    }

    public void checkout() {
        click(CHECKOUT);
    }
}
