package org.springframework.samples.petclinic.bdd;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

// class ChromeTest {

//     WebDriver driver;

//     @BeforeAll
//     static void setupClass() {
//         WebDriverManager.chromedriver().setup();
//     }

//     @BeforeEach
//     void setupTest() {
//         driver = new ChromeDriver();
//     }

//     @AfterEach
//     void teardown() {
//         driver.quit();
//     }

//     @Test
//     void test() {
//         // Exercise
//         driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
//         String title = driver.getTitle();

//         // Verify
//         assertThat(title).contains("Selenium WebDriver");
//     }

// }
@CucumberContextConfiguration
public class StepDef {

	private Scenario scenario;

	private NewOwnerPage newOwnerPage;

	private OwnerInfoPage ownerInfoPage;

	private FindOwnerPage findOwnerPage;

	private FindOwnerResultsPage findOwnerResultsPage;

	private DataManager dataManager;

	private WebDriver driver;

	// TODO: currenty tied to chrome

	public StepDef() {
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
		driver = new ChromeDriver();
		// TODO: fix me!
		this.newOwnerPage = new NewOwnerPage(driver);
		this.dataManager = DataManager.getInstance();
		this.ownerInfoPage = new OwnerInfoPage(driver);
		this.findOwnerPage = new FindOwnerPage(driver);
		this.findOwnerResultsPage = new FindOwnerResultsPage(driver);

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
		this.findOwnerResultsPage.validateOwnerResults(personas);
	}

}
