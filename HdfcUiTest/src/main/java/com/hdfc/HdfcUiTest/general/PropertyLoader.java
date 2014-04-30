package com.hdfc.HdfcUiTest.general;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


public class PropertyLoader {
	private static Properties properties = null;
	private static InputStreamReader in = null;
	
	public static void loadProperties() throws Exception{
		if(properties != null){
			return;
		}
		
		try {
		     in = new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/config/testdata.properties"));
		     properties = new Properties();
		     properties.load(in);
		} finally {
		     if (null != in) {
		         try { in.close(); } catch (IOException ex) {}
		     }
		}
	}
	
	public static String loadProperty(String key){
		try {
			loadProperties();
		} catch (Exception ex){
			//how do I handle this?
		}
		return properties.getProperty(key);
	}
	
}
