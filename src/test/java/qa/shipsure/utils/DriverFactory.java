package qa.shipsure.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Creates WebDriver instances. Selenium Manager (built into Selenium 4.6+)
 * downloads the matching driver automatically, with no WebDriverManager needed.
 */
public final class DriverFactory {
    private DriverFactory() {}

    public static WebDriver create() {
        String browser = Config.get("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(Config.get("headless", "true"));

        WebDriver driver = switch (browser) {
            case "firefox" -> {
                FirefoxOptions o = new FirefoxOptions();
                if (headless) o.addArguments("-headless");
                yield new FirefoxDriver(o);
            }
            case "edge" -> {
                EdgeOptions o = new EdgeOptions();
                if (headless) o.addArguments("--headless=new");
                yield new EdgeDriver(o);
            }
            default -> {
                ChromeOptions o = new ChromeOptions();
                if (headless) o.addArguments("--headless=new");
                o.addArguments("--window-size=1366,900", "--disable-notifications", "--no-sandbox", "--disable-dev-shm-usage");
                // keep Chrome's password-leak popup from blocking the checkout flow
                o.setExperimentalOption("prefs", java.util.Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "profile.password_manager_leak_detection", false));
                yield new ChromeDriver(o);
            }
        };
        driver.manage().window().maximize();
        return driver;
    }
}
