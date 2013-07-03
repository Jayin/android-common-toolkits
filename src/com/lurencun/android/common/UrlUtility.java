package com.lurencun.android.common;
public class UrlUtility {

	private static final int min_leight = ".jpg".length();
	
	public static String getSuffix(String pathOrName){
		if(pathOrName == null || !pathOrName.contains(".") || min_leight > pathOrName.length()) return null;
		return pathOrName.substring(pathOrName.indexOf('.'));
	}
	
	public static String genHashFileName(String pathOrName){
		int hash = pathOrName.hashCode();
		String suffix = getSuffix(pathOrName);
		return hash + (suffix == null ? "" : suffix); 
	}
	
}