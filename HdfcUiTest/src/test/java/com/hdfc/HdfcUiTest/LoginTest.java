package com.hdfc.HdfcUiTest;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hdfc.HdfcUiTest.domain.LoginFormParams;
import com.hdfc.HdfcUiTest.general.PropertyLoader;
import com.hdfc.HdfcUiTest.page.Homepage;
import com.hdfc.HdfcUiTest.page.LandingPage;
import com.hdfc.HdfcUiTest.page.LoginPage;
import com.hdfc.HdfcUiTest.page.NetBankingWelcomeScreen;

public class LoginTest {
	
	private static WebDriver driver;
	private LoginPage loginPage;

	
	@BeforeClass
	public static void initialiseClass(){
		driver = new FirefoxDriver();
	}
	
	@Before 
	public void setupTest(){
		//delete all previous cookies set by the page sign up process
    	driver.manage().deleteAllCookies();  
    	//Set implicitlyWait
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	loadFromHomepage();
	}
	
	@Test
	public void testBlankCustomerId(){
		loginPage.enterCustomerId("");
		Assert.assertTrue("Customer Id blank message not displayed correctly", loginPage.checkCustomerIdBlankMessage());
	}
	
	@Test
	public void testValidLogin(){
		loadFromHomepage();
		performLoginPageTests();
		loginPage.login(new LoginFormParams(PropertyLoader.loadProperty("customer.password"), true));
		LandingPage landingPage =  loginPage.isLoginSuccessful();
		Assert.assertNotNull("Login Success frame not loaded", landingPage);
		landingPage.switchTo();
		Assert.assertTrue("Landing page elements not displayed completly", landingPage.elementsPresent());
		landingPage.logout();
	}
	
	@Test
	public void testInvalidLogin(){
		loadFromHomepage();
		performLoginPageTests();
		loginPage.login(new LoginFormParams("", true));
		Assert.assertTrue("Passowrd alert message not correct",loginPage.blankPasswordValidationCorrect());
	}
	
	private void performLoginPageTests(){
		loginPage.enterCustomerId(PropertyLoader.loadProperty("customer.id"));
		Assert.assertTrue(loginPage.customerSpecificElementsLoaded());
		Assert.assertTrue("Customer message does not match", loginPage.doesCustomerMessageMatch());
		Assert.assertTrue("One or more Login form elements not displayed", loginPage.loginFormDisplayed());		
	}
	
	//TODO : these flow methods can actually be put in the Page classes itself
	//TODO : the asserts should be put in separate test classes, i.e. one for homepage, etc
	private LoginPage loadFromHomepage(){
		Homepage homepage = new Homepage(driver);
		homepage.load();
		Assert.assertTrue("home page not loaded", homepage.isPageLoad());
		new WebDriverWait(driver,5);
		Assert.assertTrue("Personal banking is not displayed in the landing page", homepage.isPersonalButtonVisible());
		homepage.loadPersonalBanking();
		Assert.assertTrue("Login Box is not displayed in the personal banking", homepage.isLoginBoxVisible());
		NetBankingWelcomeScreen netBankingWelcomeScreen = homepage.loadNetbankingPopup();
		Assert.assertTrue("Control is not switched to the new window", netBankingWelcomeScreen.hasLoaded());
		LoginPage loginPage = netBankingWelcomeScreen.continueToLogin();
		Assert.assertTrue("Cust ID is not displayed in the netbanking page",loginPage.customerIdElementsPresent());
		this.loginPage = loginPage;
		return loginPage;
	}
	
	@AfterClass
	public static void teardown() {
		driver.quit();
	}
}
