package org.springframework.samples.petclinic.bdd.pageobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.samples.petclinic.bdd.World;
import org.springframework.samples.petclinic.bdd.testdata.models.Persona;
import org.springframework.samples.petclinic.bdd.testdata.models.Pet;

public class FindOwnerResultsPage {

	// private WebDriver driver;
	private World world;

	@FindBy(how = How.ID, using = "owners")
	private WebElement tableOwners;

	public FindOwnerResultsPage(World world) {
		this.world = world;
		PageFactory.initElements(world.driver, this);

		// this.driver = driver;
	}
	// public void validateOwnerInfo(Persona persona) {
	// assertEquals(persona.name, this.fieldName.getText());
	// assertEquals(persona.address, this.fieldAddress.getText());
	// assertEquals(persona.city, this.fieldCity.getText());
	// assertEquals(persona.telephone, this.fieldTelephone.getText());
	// }

	public void validateOwnerResults(List<Persona> personas) {
		// change persona list into hashmap by name
		HashMap<String, Persona> personasMap = new HashMap<>();
		for (Persona p : personas) {
			personasMap.put(p.name, p);
		}

		boolean hasRow = true;
		int rowCount = 0;
		for (int i = 1; hasRow; i++) {

			WebElement name;
			WebElement address;
			WebElement city;
			WebElement telephone;
			WebElement pets;

			try {

				name = tableOwners.findElement(By.cssSelector("tbody > tr:nth-child(" + i + ") > td:nth-child(1) > a"));
				address = tableOwners.findElement(By.cssSelector("tbody > tr:nth-child(" + i + ") > td:nth-child(2)"));
				city = tableOwners.findElement(By.cssSelector("tbody > tr:nth-child(" + i + ") > td:nth-child(3)"));
				telephone = tableOwners
						.findElement(By.cssSelector("tbody > tr:nth-child(" + i + ") > td:nth-child(4)"));
				pets = tableOwners.findElement(By.cssSelector("tbody > tr:nth-child(" + i + ") > td:nth-child(5)"));

			}
			catch (NoSuchElementException e) {
				this.world.scenario.attach(e.toString(), "text/plain", "");
				hasRow = false;
				break;
			}
			Persona persona = personasMap.get(name.getText());
			// assertEquals(persona.name, this.fieldName.getText());
			assertEquals(persona.name, name.getText());
			assertEquals(persona.address, address.getText());
			assertEquals(persona.city, city.getText());
			assertEquals(persona.telephone, telephone.getText());
			// TODO: pets is annoying, more than one and can be in different orders
			HashMap<String, String> petLookup = new HashMap<>();
			petLookup.put(pets.getText(), pets.getText());
			for (Pet pet : persona.pets) {
				assertEquals(pet.name, petLookup.get(pet.name));
			}
			// get row info from result table using "i" or set hasRow to false
			// Look up persona by name
			// assert values
			// increment rowCount
			rowCount += 1;
		}
		// assert rowCount equals persona list size
		assertEquals(personas.size(), rowCount);

	}

}
