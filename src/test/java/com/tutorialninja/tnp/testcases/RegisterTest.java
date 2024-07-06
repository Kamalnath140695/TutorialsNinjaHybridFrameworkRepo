package com.tutorialninja.tnp.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountSuccessPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninja.tnp.baseclass.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	public RegisterTest() {
    super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage= homePage.selectRegisterOption();
	}
	
	@AfterMethod
	public void tearDown() {
	driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterlastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnprivacyPolicycheckBox();
		accountSuccessPage = registerPage.clickOnContinue();
		
		String actualSuccessHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
		System.out.println(actualSuccessHeading);
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account is not created");
	}
	@Test(priority = 2)
	public void verifyRegisteringAccountWithAllFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterlastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnnewsletterRadioButton();
		registerPage.clickOnprivacyPolicycheckBox();
		accountSuccessPage = registerPage.clickOnContinue();
		String actualSuccessHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
		System.out.println(actualSuccessHeading);
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account is not created");
	}
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExsistingEmailAddress() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterlastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(dataProp.getProperty("validEmailAddress"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnnewsletterRadioButton();
		registerPage.clickOnprivacyPolicycheckBox();
		accountSuccessPage = registerPage.clickOnContinue();
		String actualWarning=registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Account is not registered");
	}
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingDetails() {
		registerPage.clickOnContinue();
		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy warning message is not displayed");
		
		String actualFirstNameWarning=registerPage.retrieveFirstNameWarning();	
		Assert.assertTrue(actualFirstNameWarning.contains(dataProp.getProperty("firstNameNomatchWarning")),"First name warning message not displayed");
		
		String actualLastNameWarning=registerPage.retrieveLastNameWarning();
		Assert.assertTrue(actualLastNameWarning.contains(dataProp.getProperty("lastNameNomatchWarning")),"Last name warning message is not displayed");
		
		String actualEmailAddressWarning=registerPage.retrieveEmailAddressWarning();
		Assert.assertTrue(actualEmailAddressWarning.contains(dataProp.getProperty("EmailAddressNomatchWarning")),"Email address warning message is not displayed");
		
		String actualTelephoneWarning=registerPage.retrieveTelephoneNumberWarning();
		Assert.assertTrue(actualTelephoneWarning.contains(dataProp.getProperty("TelephoneNoMatchWarning")),"Telephone warning is not displayed");
		
		String actualPasswordWarning=registerPage.retrievepasswordNoMatchWarning();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("PasswordNoMatchWarning")),"password no match warning message is not displayed");
	}
}
