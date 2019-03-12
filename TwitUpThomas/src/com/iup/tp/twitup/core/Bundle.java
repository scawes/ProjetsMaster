package com.iup.tp.twitup.core;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;

public class Bundle {
	
	public static String get(String valeur) {
		ResourceBundle bundle;
		Properties props = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		String language = props.getProperty(Constants.CONFIGURATION_KEY_LANGUAGE);
		String country = props.getProperty(Constants.CONFIGURATION_KEY_COUNTRY);
		
		if(language.isEmpty()||country.isEmpty()) {
			bundle = ResourceBundle.getBundle("text",Locale.getDefault());
		}else {
			Locale local = new Locale(language,country);
			bundle = ResourceBundle.getBundle("text",local);
		}
		
		return bundle.getString(valeur);
	}
	
	public static void changeLanguage(String language,String country) {
		Properties props = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		props.setProperty(Constants.CONFIGURATION_KEY_LANGUAGE, language);
		props.setProperty(Constants.CONFIGURATION_KEY_COUNTRY, country);
		PropertiesManager.writeProperties(props, Constants.CONFIGURATION_FILE);
	}
}
