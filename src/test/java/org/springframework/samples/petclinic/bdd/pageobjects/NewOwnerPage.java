package org.springframework.samples.petclinic.bdd.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.samples.petclinic.bdd.World;
import org.springframework.samples.petclinic.bdd.testdata.models.Persona;

public class NewOwnerPage {

	private WebDriver driver;

	private World world;

	public NewOwnerPage(WebDriver driver, World world) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.world = world;
	}

	@FindBy(how = How.ID, using = "firstName")
	private WebElement txtbxFirstName;

	@FindBy(how = How.ID, using = "lastName")
	private WebElement txtbxLastName;

	@FindBy(how = How.ID, using = "address")
	private WebElement txtbxAddress;

	@FindBy(how = How.ID, using = "city")
	private WebElement txtbxCity;

	@FindBy(how = How.ID, using = "telephone")
	private WebElement txtbxTelephone;

	@FindBy(how = How.CSS, using = "#add-owner-form > div:nth-child(2) > div > button")
	private WebElement btnAddOwner;

	// TODO: modify to not need base URL

	public void open() {
		this.driver.navigate().to(world.baseUrl + "owners/new");
	}

	public void fillOutForm(Persona persona) {
		this.txtbxFirstName.sendKeys(persona.firstName);
		this.txtbxLastName.sendKeys(persona.lastName);
		this.txtbxAddress.sendKeys(persona.address);
		this.txtbxCity.sendKeys(persona.city);
		this.txtbxTelephone.sendKeys(persona.telephone);
		this.btnAddOwner.click();
	}

	// public void check_ShipToDifferentAddress(boolean value) {
	// if(!value) chkbx_ShipToDifferetAddress.click();
	// try { Thread.sleep(3000);}
	// catch (InterruptedException e) {}
	// }

	// public void select_Country(String countryName) {
	// drpdwn_CountryDropDownArrow.click();
	// try { Thread.sleep(2000);}
	// catch (InterruptedException e) {}

	// for(WebElement country : country_List){
	// if(country.getText().equals(countryName)) {
	// country.click();
	// try { Thread.sleep(3000);}
	// catch (InterruptedException e) {}
	// break;
	// }
	// }
	// }

	// public void select_County(String countyName) {
	// drpdwn_CountyDropDownArrow.click();
	// try { Thread.sleep(2000);}
	// catch (InterruptedException e) {}

	// for(WebElement county : country_List){
	// if(county.getText().equals(countyName)) {
	// county.click();
	// try { Thread.sleep(3000);}
	// catch (InterruptedException e) {}
	// break;
	// }
	// }
	// }

	// public void select_PaymentMethod(String paymentMethod) {
	// if(paymentMethod.equals("CheckPayment")) {
	// paymentMethod_List.get(0).click();
	// }else if(paymentMethod.equals("Cash")) {
	// paymentMethod_List.get(1).click();
	// }else {
	// new Exception("Payment Method not recognised : " + paymentMethod);
	// }
	// try { Thread.sleep(3000);}
	// catch (InterruptedException e) {}

	// }

	// public void check_TermsAndCondition(boolean value) {
	// if(value) chkbx_AcceptTermsAndCondition.click();
	// }

	// public void clickOn_PlaceOrder() {
	// btn_PlaceOrder.submit();
	// }

	// public void fill_PersonalDetails() {
	// enter_Name("Aotomation");
	// enter_LastName("Test");
	// enter_Phone("0000000000");
	// enter_Email("Automation@gmail.com");
	// select_Country("India");
	// enter_City("Delhi");
	// enter_Address("Shalimar Bagh");
	// enter_PostCode("110088");
	// select_County("Delhi");

	// }

}