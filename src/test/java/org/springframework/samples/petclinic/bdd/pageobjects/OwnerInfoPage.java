package org.springframework.samples.petclinic.bdd.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.samples.petclinic.bdd.World;
import org.springframework.samples.petclinic.bdd.testdata.models.Persona;
import org.springframework.samples.petclinic.bdd.testdata.models.Pet;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class OwnerInfoPage {

	public WebDriver driver;

	public World world;

	@FindBy(how = How.CSS, using = "body > div > div > h2:nth-child(1)")
	private WebElement titleOwnerInfo;

	// body > div > div > table:nth-child(2) > tbody > tr:nth-child(1) > td > b
	@FindBy(how = How.CSS, using = "body > div > div > table:nth-child(2) > tbody > tr:nth-child(1) > td > b")
	private WebElement fieldName;

	@FindBy(how = How.CSS, using = "body > div > div > table:nth-child(2) > tbody > tr:nth-child(2) > td")
	private WebElement fieldAddress;

	@FindBy(how = How.CSS, using = "body > div > div > table:nth-child(2) > tbody > tr:nth-child(3) > td")
	private WebElement fieldCity;

	@FindBy(how = How.CSS, using = "body > div > div > table:nth-child(2) > tbody > tr:nth-child(4) > td")
	private WebElement fieldTelephone;

	@FindBy(how = How.XPATH, using = "/html/body/div/div/table[2]")
	private WebElement tablePets;

	public OwnerInfoPage(World world) {
		this.driver = world.driver;
		this.world = world;
		// changed
		PageFactory.initElements(driver, this);
	}

	public void validateOwnerInfo(Persona persona) {
		assertEquals(persona.name, this.fieldName.getText());
		assertEquals(persona.address, this.fieldAddress.getText());
		assertEquals(persona.city, this.fieldCity.getText());
		assertEquals(persona.telephone, this.fieldTelephone.getText());

		if (persona.pets != null && persona.pets.size() > 0) {

			boolean hasRow = true;
			int rowCount = 0;

			HashMap<String, Pet> petLookup = new HashMap<>();
			for (int i = 1; hasRow; i++) {

				try {
					Pet pet = new Pet();

					pet.name = tablePets
							.findElement(By.cssSelector(
									"tbody > tr:nth-child(" + i + ") > td:nth-child(1) > dl > dd:nth-child(2)"))
							.getText();
					pet.birthDate = tablePets
							.findElement(By.cssSelector(
									"tbody > tr:nth-child(" + i + ") > td:nth-child(1) > dl > dd:nth-child(4)"))
							.getText();
					String petTypeString = tablePets
							.findElement(By.cssSelector(
									"tbody > tr:nth-child(" + i + ") > td:nth-child(1) > dl > dd:nth-child(6)"))
							.getText();

					pet.petType = Pet.PetType.valueOf(petTypeString.toUpperCase());

					petLookup.put(pet.name, pet);

				}
				catch (NoSuchElementException e) {
					this.world.scenario.attach(e.toString(), "text/plain", "");
					hasRow = false;
					break;
				}
				rowCount += 1;
			}
			for (Pet pet : persona.pets) {
				this.world.scenario.attach(pet.toString(), "text/plain", "expected");
				this.world.scenario.attach(petLookup.get(pet.name).toString(), "text/plain", "actual");
				this.world.scenario.attach(pet.petType.toString(), "text/plain", "expected");

				assertEquals(pet, petLookup.get(pet.name));
			}
			assertEquals(persona.pets.size(), petLookup.size());
		}

		// hashtable for personas pets for easy lookup
		// use persona to decide when to do check
		// do a pet count to make sure expected and actual are equal
		// code below from FindOwnerResults, but for a list not a seperate table
	}
	// pets = tableOwners.findElement(By.cssSelector("tbody > tr:nth-child(" + i +
	// ") >
	// td:nth-child(5)"));

	// }
	// catch (NoSuchElementException e) {
	// this.world.scenario.attach(e.toString(), "text/plain", "");
	// hasRow = false;
	// break;
	// }
	// Persona persona = personasMap.get(name.getText());
	// // assertEquals(persona.name, this.fieldName.getText());
	// assertEquals(persona.name, name.getText());
	// assertEquals(persona.address, address.getText());
	// assertEquals(persona.city, city.getText());
	// assertEquals(persona.telephone, telephone.getText());
	// // TODO: pets is annoying, more than one and can be in different orders
	// HashMap<String, String> petLookup = new HashMap<>();
	// petLookup.put(pets.getText(), pets.getText());
	// for (Pet pet : persona.pets) {
	// assertEquals(pet.name, petLookup.get(pet.name));
	// }
	// public void open() {
	// this.driver.navigate().to("http://localhost:8080/owners/new");
	// }

}
