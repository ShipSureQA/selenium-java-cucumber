package qa.shipsure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import qa.shipsure.utils.Config;

public class LoginPage extends BasePage {
    private static final By USERNAME = dataTest("username");
    private static final By PASSWORD = dataTest("password");
    private static final By LOGIN_BUTTON = dataTest("login-button");
    private static final By ERROR = dataTest("error");

    public LoginPage(WebDriver driver) { super(driver); }

    public void open() {
        driver.get(Config.get("baseUrl"));
        visible(USERNAME);
    }

    public void login(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
    }

    public String errorMessage() {
        return text(ERROR);
    }
}
