package com.hdfc.HdfcUiTest.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends PageBase {

	private static final String PAGE_TITLE = "HDFC Bank: Personal Banking Services";
	
	@FindBy(linkText = "Personal")
	private WebElement personalWE;	
	
	@FindBy(className = "loginbox")
	private WebElement loginBoxWE;
	
	@FindBy(id = "netbanking")
	private WebElement netbankingChkBoxWE;
	
	@FindBy(id = "loginsubmit")
	protected WebElement loginButtonWE;
	
	public Homepage(WebDriver driver) {
		super(driver, PAGE_TITLE);
		PageFactory.initElements(driver, this);
	}
	
	public void load() {
		driver.get("http://www.hdfcbank.com");
	}
	
	public void loadPersonalBanking(){
		personalWE.click();
	}
	
	public boolean isPersonalButtonVisible(){
		return personalWE.isDisplayed();
	}
	
	public boolean isLoginBoxVisible(){
		return loginBoxWE.isDisplayed();
	}
	
	public NetBankingWelcomeScreen loadNetbankingPopup() {		
		
		netbankingChkBoxWE.click();
		loginButtonWE.click();
		//String windowHandle = driver.getWindowHandle();
		String lastWindow = "";		
		for(String winHandle : driver.getWindowHandles()){
		    lastWindow = winHandle;
		}
		driver.switchTo().window(lastWindow);

		return new NetBankingWelcomeScreen(driver);
	}	
}
