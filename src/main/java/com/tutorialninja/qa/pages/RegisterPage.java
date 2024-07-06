package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegisterPage {
	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneNumberField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath ="//input[@type='checkbox']")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "(//input[@value='1'])[2]")
	private WebElement newLetterButton;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPoilcyWarning;
	
	@FindBy(xpath = "//*[@id=\"account\"]/div[2]/div/div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "/html/body/div[2]/div[2]/div/form/fieldset[1]/div[3]/div/div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "/html/body/div[2]/div[2]/div/form/fieldset[1]/div[4]/div/div")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath="/html/body/div[2]/div[2]/div/form/fieldset[1]/div[5]/div/div")
	private WebElement telephoneNumberWarning;
	
	@FindBy(xpath="/html/body/div[2]/div[2]/div/form/fieldset[2]/div[1]/div/div")
	private WebElement passwordNoMatchWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retrievePrivacyPolicyWarning() {
		String PrivacyPolicyWarningtext = privacyPoilcyWarning.getText();
		return PrivacyPolicyWarningtext;
	}
	public String retrieveFirstNameWarning() {
		String retrieveFirstNameWarningtext = firstNameWarning.getText();
		return retrieveFirstNameWarningtext;
	}
	
	public String retrieveLastNameWarning() {
		String retrieveLastNameWarningText=lastNameWarning.getText();
		return retrieveLastNameWarningText;
	}
	
	public String retrieveEmailAddressWarning() {
		String emailAddressWarningtext = emailAddressWarning.getText();
		return emailAddressWarningtext;
	}
	
	public String retrieveTelephoneNumberWarning() {
		String TelephoneNumberWarningtext = telephoneNumberWarning.getText();
		return TelephoneNumberWarningtext;
	}
	public String retrievepasswordNoMatchWarning() {
		String retrievepasswordNoMatchWarningtext = passwordNoMatchWarning.getText();
		return retrievepasswordNoMatchWarningtext;
	}
	
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	public void enterlastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	public void enterEmailAddress(String emailAddressText) {
		emailAddressField.sendKeys(emailAddressText);
	} 
	public void enterTelephoneNumber(String telephoneNumberText) {
		telephoneNumberField.sendKeys(telephoneNumberText);
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}
	public void clickOnprivacyPolicycheckBox() {
		privacyPolicyField.click();
	}
	public AccountSuccessPage clickOnContinue() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	public void clickOnnewsletterRadioButton() {
		newLetterButton.click();
	}
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningtext = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningtext;
	}
}
