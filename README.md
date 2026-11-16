# Selenium + Java + Cucumber BDD Framework (Demo)

![Tests](https://github.com/ShipSureQA/selenium-java-cucumber/actions/workflows/tests.yml/badge.svg)

> **Demo / self-initiated project** by ShipSure QA. Shows how I structure a maintainable BDD automation framework. It tests [saucedemo.com](https://www.saucedemo.com), a public practice site built for test automation. It is not client work.

## Highlights
- **Cucumber BDD**: readable Gherkin feature files that business stakeholders can review
- **Page Object Model** with a `BasePage` of reusable, explicit-wait helpers (no `Thread.sleep`)
- **Dependency injection** (PicoContainer): one `TestContext` per scenario, shared across step classes
- **Data-driven tests**: Scenario Outlines with Examples tables, plus DataTables for product lists
- **Parallel execution**: TestNG DataProvider runs scenarios concurrently, each with its own browser
- **Cross-browser**: Chrome, Firefox, Edge via `-Dbrowser=`; Selenium Manager handles drivers automatically
- **Config-driven**: `config.properties`, any value overridable from the command line
- **Reporting**: Cucumber HTML + JSON reports, screenshots attached on failure, rerun file for failed scenarios
- **CI**: GitHub Actions matrix (Chrome + Firefox) on push, PR and a weekly schedule

## Coverage: 15 scenarios
| Feature | Scenarios |
|---|---|
| Login | valid login, locked-out user, 3 validation cases (outline) |
| Inventory | product count, 4 sort options (outline), cart badge add/remove |
| Checkout | full end-to-end purchase with total = item total + tax check, 3 form validation cases (outline) |

Tags: `@smoke` (critical path), `@e2e`, `@login`, `@inventory`, `@checkout`

## Run
Requires Java 17+ and Maven.
```bash
mvn test                                         # all scenarios, headless Chrome
mvn test -Dbrowser=firefox                       # another browser
mvn test -Dheadless=false                        # watch it run
mvn test -Dcucumber.filter.tags="@smoke"         # smoke suite only
```
Report: `target/cucumber-report.html`

## Structure
```
src/test/java/qa/shipsure/
  pages/      BasePage + page objects (Login, Inventory, Cart, Checkout)
  steps/      Step definitions
  hooks/      Screenshot-on-failure + browser teardown
  context/    TestContext (per-scenario driver & pages, injected by PicoContainer)
  runners/    TestNG Cucumber runner (parallel)
  utils/      Config reader, DriverFactory
src/test/resources/
  features/   Gherkin feature files
  config.properties
```
