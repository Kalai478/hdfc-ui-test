package com.hdfc.HdfcUiTest.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends PageBase {

	private static final String PAGE_TITLE = null;


	@FindBy(xpath = "//img[@alt='Log Out']")
	protected WebElement logoutWE;
	
	@FindBy(name = "img_logo")
	protected WebElement welcomeWE;
	
	@FindBy(xpath = "//frame[@name='common_menu1']")
	protected WebElement frame1WE;	
	
	public LandingPage(WebDriver driver){
		super(driver, PAGE_TITLE);
		PageFactory.initElements(driver, this);
	}

	public void switchTo(){
		driver.switchTo().frame(frame1WE);
	}
	
	public boolean elementsPresent(){
		return welcomeWE.isDisplayed() && logoutWE.isDisplayed();
	}
	
	public void logout(){
		logoutWE.click();
	}
	
}
