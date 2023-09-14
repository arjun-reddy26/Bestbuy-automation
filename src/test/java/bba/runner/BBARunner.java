package bba.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {
		"src/test/resources/FeatureFiles/OrderProducts.feature" }, glue = "bba.stepdefinitions", plugin = {
				"html:target/cucumber-html-report.html", "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
				"junit:target/cucumber-results.xml" })
public class BBARunner extends AbstractTestNGCucumberTests {

}
