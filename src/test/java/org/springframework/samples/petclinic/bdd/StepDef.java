package org.springframework.samples.petclinic.bdd;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.samples.petclinic.bdd.testdata.DataManager;
import org.springframework.samples.petclinic.bdd.testdata.models.Persona;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
public class StepDef {

	private Scenario scenario;

	// private NewOwnerPage newOwnerPage;
	private DataManager dataManager;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
		// this.newOwnerPage = new newOwnerPage(scenario.);
		this.dataManager = DataManager.getInstance();
	}

	@Given("they see the add owner section")
	public void they_see_the_add_owner_section() {
		List<String> list = new ArrayList<String>();
		list.add("false email");
		Persona persona = this.dataManager.getModdedPersona("Mabel Pines", list);
		System.out.println("email: " + persona.email);
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("they submit {string} as a new owner")
	public void they_submit_as_a_new_owner(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("they see the {string} profile")
	public void they_see_the_profile(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}
