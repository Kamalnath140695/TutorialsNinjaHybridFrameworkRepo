package com.tutorialninja.tnp.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.SearchPage;
import com.tutorialninja.tnp.baseclass.Base;

//Updated comment - Added more details

public class SearchTest extends Base {
	
	public WebDriver driver;
	 SearchPage searchPage;
	public SearchTest() {
	super();
	}
	
	@BeforeMethod
	public void setUp() {
        driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));   
	}
	
	@AfterMethod
	public void tearDown() {
     driver.quit();
	}
 @Test(priority = 1)	
   public void verifySearchWithValidProduct() {
	 HomePage homePage=new HomePage(driver);
	 homePage.enterSearchBox(dataProp.getProperty("validProduct"));
	 searchPage = homePage.ClickOnSearchButton();
	 Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"product search results is not displayed");
}
 
 @Test(priority = 2)
 public void verifySearchWithInvalidProduct() {
	 HomePage homePage=new HomePage(driver);
	 homePage.enterSearchBox(dataProp.getProperty("invalidProduct"));
	 searchPage =homePage.ClickOnSearchButton();
	 String actualSearchMessage=searchPage.retrieveNoProductMessage();
	 Assert.assertTrue(actualSearchMessage.contains(dataProp.getProperty("ss")),"No Product search results message is not displayed");
}
 
 @Test(priority = 3,dependsOnMethods = {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
 public void verifySearchWithoutAnyProduct() {
	 HomePage homePage=new HomePage(driver);
	 searchPage=homePage.ClickOnSearchButton();
	 String actualSearchMessage=searchPage.retrieveNoProductMessage();
	 Assert.assertTrue(actualSearchMessage.contains(dataProp.getProperty("noProductTextInSearchResults")),"No Product search results message is not displayed");
}
}
