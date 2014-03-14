package com.pmis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	private static final PropertiesUtil prop = new PropertiesUtil();
	private static Properties p = new Properties();
	private PropertiesUtil() {
			
	}
	public static PropertiesUtil getInstance(){
		InputStream inputStream  = new PropertiesUtil().getClass().getClassLoader().getResourceAsStream("com/pmis/client.properties"); 
		try {
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public String getProperty(String key){
		return p.getProperty(key);
	}
	
	
}
