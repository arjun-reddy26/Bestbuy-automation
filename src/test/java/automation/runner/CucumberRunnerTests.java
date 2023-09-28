package automation.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions(features = {"src/test/resources/scenarios/buyphones.feature"}, glue = {"automation.steps"})
    
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
    
}
