package org.springframework.samples.petclinic.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.samples.petclinic.bdd.testdata.models.Persona;
import java.util.List;
import static org.junit.Assert.*;

public class OwnerInfoPage {

	public WebDriver driver;

	@FindBy(how = How.CSS, using = "body > div > div > h2:nth-child(1)")
	private WebElement titleOwnerInfo;

	@FindBy(how = How.CSS, using = "body > div > div > table:nth-child(2) > tbody > tr:nth-child(1) > td > b")
	private WebElement fieldName;

	@FindBy(how = How.CSS, using = "body > div > div > table:nth-child(2) > tbody > tr:nth-child(2) > td")
	private WebElement fieldAddress;

	@FindBy(how = How.CSS, using = "body > div > div > table:nth-child(2) > tbody > tr:nth-child(3) > td")
	private WebElement fieldCity;

	@FindBy(how = How.CSS, using = "body > div > div > table:nth-child(2) > tbody > tr:nth-child(4) > td")
	private WebElement fieldTelephone;

	public OwnerInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void validateOwnerInfo(Persona persona) {
		assertEquals(persona.name, this.fieldName.getText());
		assertEquals(persona.address, this.fieldAddress.getText());
		assertEquals(persona.city, this.fieldCity.getText());
		assertEquals(persona.telephone, this.fieldTelephone.getText());
	}

	// public void open() {
	// this.driver.navigate().to("http://localhost:8080/owners/new");
	// }

}
