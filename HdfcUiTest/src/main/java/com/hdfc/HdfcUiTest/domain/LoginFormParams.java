package com.hdfc.HdfcUiTest.domain;

public class LoginFormParams {

	private String password;
	private boolean isCustomerMessageCorrect;
	
	public LoginFormParams(String password, boolean isCustomerMessageCorrect){
		this.password = password;
		this.isCustomerMessageCorrect = isCustomerMessageCorrect;
	}
	
	public String getPassword() {
		return password;
	}
	public boolean getIsCustomerMessageCorrect() {
		return isCustomerMessageCorrect;
	}
	
	
	
}
