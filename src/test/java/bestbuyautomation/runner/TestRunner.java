package bestbuyautomation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {
		"src/test/resources/FeatureFiles/BestBuyOrderPorduct.feature" }, glue = "bestbuyautomation.stepdefinitions", plugin = {
				"pretty", "html:target/cucumber-html-report.html", "json:target/cucumber.json",
				"pretty:target/cucumber-pretty.txt", "junit:target/cucumber-results.xml" })
public class TestRunner extends AbstractTestNGCucumberTests {

}
