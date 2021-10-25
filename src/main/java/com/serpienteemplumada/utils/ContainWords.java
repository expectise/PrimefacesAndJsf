package com.serpienteemplumada.utils;

public class ContainWords {
	public final static boolean Contains(String inputString, String[] items) {
	    boolean found = false;
	    for (String item : items) {
	        if (inputString.contains(item)) {
	            found = true;
	            break;
	        }
	    }
	    return found;
	}
}
