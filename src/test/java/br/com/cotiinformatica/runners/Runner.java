package br.com.cotiinformatica.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/br/com/cotiinformatica/features",
		glue = "br/com/cotiinformatica/steps",
		plugin = {
		        "pretty",
		        "html:target/cucumber-reports/cucumber-report.html",
		        "json:target/cucumber-reports/cucumber-report.json",
		        "junit:target/cucumber-reports/cucumber-report.xml"
		    }
		)
public class Runner {
}
