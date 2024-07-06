package com.tutorialninja.tnp.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninja.tnp.baseclass.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		loginPage = homepage.navigateToLoginPage();
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
    Object[][] data= Utilities.getTestDataFromExcel("Login");
	return data;
	}
	
	@Test(priority = 1,dataProvider = "validCredentialsSupplier")
  	public void verifyLoginWithValidCredentials(String email, String password) {
		accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
  	}
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		 accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		String actualWarningMessage = loginPage.retrieveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");	
	}
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		String actualWarningMessage = loginPage.retrieveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");	
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();	
		String actualWarningMessage = loginPage.retrieveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");	
	}
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
		loginPage.clickOnLoginButton();	
		String actualWarningMessage = loginPage.retrieveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");
	}
}
