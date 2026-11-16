package qa.shipsure.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutPage extends BasePage {
    private static final By FIRST_NAME = dataTest("firstName");
    private static final By LAST_NAME = dataTest("lastName");
    private static final By POSTAL_CODE = dataTest("postalCode");
    private static final By CONTINUE = dataTest("continue");
    private static final By ERROR = dataTest("error");
    private static final By SUBTOTAL = dataTest("subtotal-label");
    private static final By TAX = dataTest("tax-label");
    private static final By TOTAL = dataTest("total-label");
    private static final By FINISH = dataTest("finish");
    private static final By COMPLETE_HEADER = dataTest("complete-header");

    private static final Pattern AMOUNT = Pattern.compile("\\$([\\d.]+)");

    public CheckoutPage(WebDriver driver) { super(driver); }

    public void enterDetails(String first, String last, String postal) {
        type(FIRST_NAME, first);
        type(LAST_NAME, last);
        type(POSTAL_CODE, postal);
        click(CONTINUE);
    }

    public String errorMessage() { return text(ERROR); }

    private double amount(By locator) {
        Matcher m = AMOUNT.matcher(text(locator));
        if (!m.find()) throw new IllegalStateException("No amount in: " + text(locator));
        return Double.parseDouble(m.group(1));
    }

    public double subtotal() { return amount(SUBTOTAL); }
    public double tax() { return amount(TAX); }
    public double total() { return amount(TOTAL); }

    public void finish() { click(FINISH); }

    public String confirmationMessage() {
        wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
        return text(COMPLETE_HEADER);
    }
}
