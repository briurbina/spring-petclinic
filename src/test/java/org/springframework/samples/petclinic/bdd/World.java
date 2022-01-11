package org.springframework.samples.petclinic.bdd;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import io.cucumber.java.Scenario;
import io.cucumber.spring.ScenarioScope;

@Component
@ScenarioScope
public class World {

	public String baseUrl;

	public WebDriver driver;

	public Scenario scenario;

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
