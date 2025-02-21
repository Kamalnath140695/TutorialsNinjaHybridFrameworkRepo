package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	//objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver) {
    this.driver=driver;
    PageFactory.initElements(driver,this);
  
	}
	//actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	public LoginPage navigateToLoginPage() {
			myAccountDropMenu.click();
			loginOption.click();
			return new LoginPage(driver);
	}
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}
	public void enterSearchBox(String searchText) {
		searchBoxField.sendKeys(searchText);
	}
	public SearchPage ClickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
}
