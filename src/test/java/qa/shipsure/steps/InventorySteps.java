package qa.shipsure.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qa.shipsure.context.TestContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class InventorySteps {
    private final TestContext ctx;

    public InventorySteps(TestContext ctx) { this.ctx = ctx; }

    @Then("I should see {int} products")
    public void iShouldSeeProducts(int count) {
        assertEquals(ctx.inventoryPage().productCount(), count);
    }

    @When("I sort products by {string}")
    public void iSortProductsBy(String option) {
        ctx.inventoryPage().sortBy(option);
    }

    @Then("product prices should be in {word} order")
    public void pricesShouldBeInOrder(String direction) {
        List<Double> actual = ctx.inventoryPage().productPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder());
        assertEquals(actual, expected, "Prices are not in " + direction + " order");
    }

    @Then("product names should be in {word} order")
    public void namesShouldBeInOrder(String direction) {
        List<String> actual = ctx.inventoryPage().productNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(direction.equals("ascending") ? Comparator.naturalOrder() : Comparator.reverseOrder());
        assertEquals(actual, expected, "Names are not in " + direction + " order");
    }

    @When("I add {string} to the cart")
    public void iAddToTheCart(String product) {
        ctx.inventoryPage().addToCart(product);
    }

    @When("I add the following products to the cart:")
    public void iAddProducts(List<String> products) {
        products.forEach(ctx.inventoryPage()::addToCart);
    }

    @When("I remove {string} from the cart")
    public void iRemoveFromTheCart(String product) {
        ctx.inventoryPage().removeFromCart(product);
    }

    @Then("the cart badge should show {int}")
    public void theCartBadgeShouldShow(int count) {
        assertEquals(ctx.inventoryPage().cartCount(), count);
    }
}
