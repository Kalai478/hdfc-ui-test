package com.hdfc.HdfcUiTest.page;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hdfc.HdfcUiTest.domain.LoginFormParams;
import com.hdfc.HdfcUiTest.general.PropertyLoader;

public class LoginPage extends PageBase {

	private static final String PAGE_TITLE = "";
	
	public LoginPage(WebDriver driver){
		super(driver, PAGE_TITLE);
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "fldLoginUserId")
	protected WebElement custIDTextboxWE;
	
	@FindBy(name = "fldPassword")
	protected WebElement custPassTextboxWE;
	
	@FindBy(xpath = "//img[@alt='continue']")
	protected WebElement custContinueButtonWE;
	
	@FindBy(xpath = "//img[contains(@src,'https://www.services.cyota.co.uk/generic/images/efamily/stu')]")
	protected WebElement custSpecificImageWE;
	
	@FindBy(xpath = "//table[@class='phrase_box_table']")
	protected WebElement custSpecificMessageWE;
	
	@FindBy(id = "chkrsastu")
	protected WebElement custConfirmBoxWE;
	
	@FindBy(xpath = "//img[@alt='Log In']")
	protected WebElement custConfirmLoginButtonWE;
	
	@FindBy(className = "loginLink")
	protected WebElement welcomenoteWE;
	
	@FindBy(name = "save")
	protected WebElement savingsWE;
	
	@FindBy(xpath ="//img[contains(@src,'login.gif')]")
	protected WebElement invalidLoginWE;
	
	@FindBy(xpath = "//frame[@name='common_menu1']")
	protected WebElement frame1WE;	
	
	public boolean customerIdElementsPresent() {
		return custIDTextboxWE.isDisplayed() && custContinueButtonWE.isDisplayed();
	}
	
	public boolean customerSpecificElementsLoaded(){
		try {
			return custSpecificMessageWE.isDisplayed() && custSpecificImageWE.isDisplayed();
		} catch (Exception ex){
			Logger.getLogger("info").log(Level.INFO, "exception checking customer specific elements - cause " + ex.getCause() + " message : " + ex.getMessage());
			return false;
		}
	}
	
	public void resetElements(){
		custIDTextboxWE.clear();
	}
	
	public void enterCustomerId(String customerId){
		custIDTextboxWE.sendKeys(customerId);
		custContinueButtonWE.click();
		new WebDriverWait(driver, 10);
	}
	
	public boolean doesCustomerMessageMatch(){
		return custSpecificMessageWE.getText().contains(PropertyLoader.loadProperty("customer.specific.message"));
	}
	
	public boolean loginFormDisplayed(){
		return custPassTextboxWE.isDisplayed() && custConfirmBoxWE.isDisplayed() && custConfirmLoginButtonWE.isDisplayed();
	}
	
	public void login(LoginFormParams loginFormParams){
		custPassTextboxWE.sendKeys(loginFormParams.getPassword());
		if(loginFormParams.getIsCustomerMessageCorrect()) {
			custConfirmBoxWE.click();
		}
		custConfirmLoginButtonWE.click();
	}
	
	public LandingPage isLoginSuccessful(){
		return frame1WE.isDisplayed() ? new LandingPage(driver) : null;
	}
	
	public boolean checkCustomerIdBlankMessage(){
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		alert.accept();
		return PropertyLoader.loadProperty("customer.id.validation.text").equals(alertMessage);
	}
	
	public boolean blankPasswordValidationCorrect(){
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		return PropertyLoader.loadProperty("blank.password.validation.text").equals(alertMessage);
	}
	
}
