package qa.shipsure.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import qa.shipsure.context.TestContext;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) { this.context = context; }

    /** Attach a screenshot to the Cucumber report on failure, then close the browser. */
    @After
    public void tearDown(Scenario scenario) {
        if (!context.hasDriver()) return;
        try {
            if (scenario.isFailed()) {
                byte[] png = ((TakesScreenshot) context.driver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", scenario.getName());
            }
        } finally {
            context.quitDriver();
        }
    }
}
