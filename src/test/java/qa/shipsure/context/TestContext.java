package qa.shipsure.context;

import org.openqa.selenium.WebDriver;
import qa.shipsure.pages.CartPage;
import qa.shipsure.pages.CheckoutPage;
import qa.shipsure.pages.InventoryPage;
import qa.shipsure.pages.LoginPage;
import qa.shipsure.utils.DriverFactory;

/**
 * Shared state for one scenario. PicoContainer injects a fresh instance
 * into every step class, so parallel scenarios never share a browser.
 */
public class TestContext {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public WebDriver driver() {
        if (driver == null) driver = DriverFactory.create();
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public boolean hasDriver() { return driver != null; }

    public LoginPage loginPage() {
        if (loginPage == null) loginPage = new LoginPage(driver());
        return loginPage;
    }

    public InventoryPage inventoryPage() {
        if (inventoryPage == null) inventoryPage = new InventoryPage(driver());
        return inventoryPage;
    }

    public CartPage cartPage() {
        if (cartPage == null) cartPage = new CartPage(driver());
        return cartPage;
    }

    public CheckoutPage checkoutPage() {
        if (checkoutPage == null) checkoutPage = new CheckoutPage(driver());
        return checkoutPage;
    }
}
