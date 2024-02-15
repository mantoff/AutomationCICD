package bg.mantov.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/bg/mantov/cucumber", glue = "bg.mantov.stepdefinitions",
monochrome = true, tags = "@Regression", plugin = {"html:reports/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
