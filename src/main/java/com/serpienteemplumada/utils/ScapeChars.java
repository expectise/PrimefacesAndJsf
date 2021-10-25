package com.serpienteemplumada.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ScapeChars {
	public static final Pattern DIACRITICS_AND_FRIENDS
    = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

	
	public static String stripDiacritics(String str, String archivo) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
		
		if (archivo.contains("jpg")) {
			str += ".jpg";
		}else {
			str += ".png";
		}
		
		String noSpaceStr = str.replaceAll("\\s", "-");
		
		return noSpaceStr.toLowerCase();
	}
	
	public static String stripDiacritics(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
		
		String noSpaceStr = str.replaceAll("\\s", "-");
		
		return noSpaceStr.toLowerCase();
	}

}
