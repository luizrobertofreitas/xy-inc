package com.xyinc.util;

import java.util.ResourceBundle;

public final class AppUtil {
	
	private static final String PROPERTIES_FILENAME = "application";
	
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILENAME);
	
	private AppUtil() {}
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
