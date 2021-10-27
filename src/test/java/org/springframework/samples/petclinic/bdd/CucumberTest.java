package org.springframework.samples.petclinic.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

/**
 * To run cucumber test.
 */
@RunWith(Cucumber.class)
// @CucumberContextConfiguration
@CucumberOptions(plugin = { "pretty", "json:target/cucumber-reports/cucumber.json" },
		// location
		// of
		// test
		// result
		// data
		// output
		features = { "classpath:features" }, // location of feature files
		glue = { "org.springframework.samples.petclinic.bdd" }) // location of step
// implementation

public class CucumberTest {

}
