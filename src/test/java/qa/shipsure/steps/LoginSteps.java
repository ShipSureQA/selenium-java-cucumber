package qa.shipsure.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qa.shipsure.context.TestContext;
import qa.shipsure.utils.Config;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps {
    private final TestContext ctx;

    public LoginSteps(TestContext ctx) { this.ctx = ctx; }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        ctx.loginPage().open();
    }

    @When("I log in as {string} with password {string}")
    public void iLogInAs(String username, String password) {
        ctx.loginPage().login(username, password);
    }

    @When("I log in as {string}")
    public void iLogInWithDefaultPassword(String username) {
        ctx.loginPage().login(username, Config.get("password"));
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        ctx.loginPage().open();
        ctx.loginPage().login(username, Config.get("password"));
        assertTrue(ctx.inventoryPage().isLoaded(), "Inventory page did not load after login");
    }

    @Then("I should see the products page")
    public void iShouldSeeTheProductsPage() {
        assertTrue(ctx.inventoryPage().isLoaded(), "Expected to land on the products page");
    }

    @Then("I should see the login error {string}")
    public void iShouldSeeTheLoginError(String expected) {
        String actual = ctx.loginPage().errorMessage();
        assertTrue(actual.contains(expected), "Expected error containing '" + expected + "' but was '" + actual + "'");
    }

    @Then("I should stay on the login page")
    public void iShouldStayOnTheLoginPage() {
        assertEquals(ctx.loginPage().currentUrl().replaceAll("/$", ""), Config.get("baseUrl"));
    }
}
