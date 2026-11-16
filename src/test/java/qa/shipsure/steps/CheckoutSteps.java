package qa.shipsure.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qa.shipsure.context.TestContext;
import qa.shipsure.pages.CheckoutPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutSteps {
    private final TestContext ctx;

    public CheckoutSteps(TestContext ctx) { this.ctx = ctx; }

    @When("I open the cart")
    public void iOpenTheCart() {
        ctx.inventoryPage().openCart();
    }

    @Then("the cart should contain:")
    public void theCartShouldContain(List<String> expected) {
        assertEquals(ctx.cartPage().itemNames(), expected);
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        ctx.cartPage().checkout();
    }

    @When("I enter first name {string}, last name {string} and postal code {string}")
    public void iEnterDetails(String first, String last, String postal) {
        ctx.checkoutPage().enterDetails(first, last, postal);
    }

    @Then("the order total should equal the item total plus tax")
    public void totalShouldEqualSubtotalPlusTax() {
        CheckoutPage page = ctx.checkoutPage();
        double subtotal = page.subtotal();
        assertTrue(subtotal > 0, "Item total should be greater than 0");
        assertEquals(page.total(), subtotal + page.tax(), 0.01, "Total != item total + tax");
    }

    @When("I finish the order")
    public void iFinishTheOrder() {
        ctx.checkoutPage().finish();
    }

    @Then("I should see the confirmation {string}")
    public void iShouldSeeTheConfirmation(String message) {
        assertEquals(ctx.checkoutPage().confirmationMessage(), message);
    }

    @Then("I should see the checkout error {string}")
    public void iShouldSeeTheCheckoutError(String expected) {
        assertTrue(ctx.checkoutPage().errorMessage().contains(expected));
    }
}
