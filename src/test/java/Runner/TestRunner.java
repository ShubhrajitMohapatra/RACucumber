package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/org/example/Books.feature"},glue = {"Defination"},
        dryRun = false,
        tags = "@Authenticate", //"@CreateOrder"
        plugin = {"html:Reportoutput/APICucumber.html","junit:Reportoutput/APICucumber.xml","json:Reportoutput/APICucumber.json"}
) // You want to run all feature file then remove the feature file name ex- "/src/test/java/org/example/"
public class TestRunner {


}
