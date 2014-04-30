package com.hdfc.HdfcUiTest.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NetBankingWelcomeScreen extends PageBase{

	private static final String PAGE_TITLE = "HDFC Bank NetBanking - Internet Banking Services by HDFC Bank";

	@FindBy(xpath = "//img[contains(@src, 'netbanking_continue_red.gif')]")
	private WebElement newWindowContinueButtonWE;
	
	@FindBy(xpath = "//frame[@name='login_page']")
	private WebElement frameWE;
	
	public NetBankingWelcomeScreen(WebDriver driver){
		super(driver, PAGE_TITLE);
		PageFactory.initElements(driver, this);
	}
	
	public boolean hasLoaded(){
		return newWindowContinueButtonWE.isDisplayed();
	}
	
	public LoginPage continueToLogin(){
		driver.manage().window().maximize();
		newWindowContinueButtonWE.click();
		driver.switchTo().frame(frameWE);
		driver.switchTo().activeElement();
		return new LoginPage(driver);
	}
}
