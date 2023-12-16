
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/main/resources" }, glue = { "stepdefs" }, plugin = { "json:target/cucumber.json" })
public class RunCucumberTest {

}