package org.springframework.samples.petclinic.bdd.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FindOwnerPage {

	private WebDriver driver;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement btnFindOwner;

	@FindBy(how = How.ID, using = "lastName")
	private WebElement txtbxLastName;

	public FindOwnerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void open() {
		this.driver.navigate().to("http://localhost:8080/owners/find");
	}

	public void search(String lastName) {
		this.txtbxLastName.sendKeys(lastName);
		this.btnFindOwner.click();
	}

}
