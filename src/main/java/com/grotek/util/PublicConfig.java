package com.grotek.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PublicConfig {

	private static Properties prop;
	static{
		try {
			InputStream in = PublicConfig.class.getResourceAsStream("/config.properties"); 
			prop = new Properties();  
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public static String getImagePath(){
		return prop.getProperty("imagePath");
	}
	
	public static String getImageUrl() {
		return prop.getProperty("imageUrl");
	}	
	
	public static String getEmail(){
		return prop.getProperty("email");
	}
	
	public static String getMailuser(){
		return prop.getProperty("mailuser");
	}
	
	public static String getMailpass(){
		return prop.getProperty("mailpass");
	}
	
	public static String getMailhost(){
		return prop.getProperty("mailhost");
	}
	
	public static String getSmsaddress(){
		return prop.getProperty("smsaddress");
	}
	
	public static String getSmsaddress2(){
		return prop.getProperty("smsaddress2");
	}
	
	public static String getTemplateUrl() {
		return prop.getProperty("template");
	}	
	
	public static String getSendTemplate() {
		return prop.getProperty("sendtemplate");
	}	
	
	public static String getExportUrl(){
		return prop.getProperty("exportPath");
	}
	
	public static int getgasolineId(){
		return Integer.parseInt(prop.getProperty("gasolineID"));
	}
	
	public static String getPoster(){
		return prop.getProperty("poster");
	}
	
	public static String getMeeting(){
		return prop.getProperty("meeting");
	}
	
	public static String getGift(){
		return prop.getProperty("gift");
	}
	
	public static String getPrint(){
		return prop.getProperty("print");
	}
	public static String getOther(){
		return prop.getProperty("other");
	}
	public static String getPage(){
		return prop.getProperty("page");
	}
	public static String getSample(){
		return prop.getProperty("sample");
	}
	public static String getEntertain(){
		return prop.getProperty("entertain");
	}
}