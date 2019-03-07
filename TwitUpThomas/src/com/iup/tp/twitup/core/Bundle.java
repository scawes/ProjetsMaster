package com.iup.tp.twitup.core;

import java.util.Locale;
import java.util.ResourceBundle;

public class Bundle {
	
	public static String get(String valeur) {
		ResourceBundle bundle = ResourceBundle.getBundle("text",Locale.getDefault());
		return bundle.getString(valeur);
	}
}
