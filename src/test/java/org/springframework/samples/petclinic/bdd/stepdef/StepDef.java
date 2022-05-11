package org.springframework.samples.petclinic.bdd.stepdef;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.bdd.World;
import org.springframework.samples.petclinic.bdd.pageobjects.FindOwnerPage;
import org.springframework.samples.petclinic.bdd.pageobjects.FindOwnerResultsPage;
import org.springframework.samples.petclinic.bdd.pageobjects.NewOwnerPage;
import org.springframework.samples.petclinic.bdd.pageobjects.OwnerInfoPage;
import org.springframework.samples.petclinic.bdd.testdata.DataManager;
import org.springframework.samples.petclinic.bdd.testdata.models.DataChunk;
import org.springframework.samples.petclinic.bdd.testdata.models.Persona;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDef {

	private NewOwnerPage newOwnerPage;

	private OwnerInfoPage ownerInfoPage;

	private FindOwnerPage findOwnerPage;

	private FindOwnerResultsPage findOwnerResultsPage;

	private DataManager dataManager;

	@Autowired
	public StepDef(World world) {
		WebDriver driver = world.driver;
		this.newOwnerPage = new NewOwnerPage(driver, world);
		this.dataManager = DataManager.getInstance();
		this.ownerInfoPage = new OwnerInfoPage(world);
		this.findOwnerPage = new FindOwnerPage(driver, world);
		this.findOwnerResultsPage = new FindOwnerResultsPage(world);
	}

	@Given("they see the add owner section")
	public void they_see_the_add_owner_section() {
		this.newOwnerPage.open();
	}

	@When("they submit {string} as a new owner")
	public void they_submit_as_a_new_owner(String personaName) {
		Persona persona = this.dataManager.findPersonaByNameOrAlias(personaName);
		this.newOwnerPage.fillOutForm(persona);
	}

	@Then("they see the {string} profile")
	public void they_see_the_profile(String personaName) {
		Persona persona = this.dataManager.findPersonaByNameOrAlias(personaName);
		this.ownerInfoPage.validateOwnerInfo(persona);
	}

	@Given("they see the find owners section")
	public void they_see_the_find_owners_section() {
		this.findOwnerPage.open();
	}

	@When("they search for {string}")
	public void they_search_for(String string) {
		this.findOwnerPage.search(string);
	}

	@Then("they see the results for a {string}")
	public void they_see_the_results_for_a(String string) {
		List<Persona> personas = this.dataManager.getPersonasByChunkName(string);
		if (personas.size() == 1) {
			this.ownerInfoPage.validateOwnerInfo(personas.iterator().next());
		}
		else {
			this.findOwnerResultsPage.validateOwnerResults(personas);
		}
	}

}
