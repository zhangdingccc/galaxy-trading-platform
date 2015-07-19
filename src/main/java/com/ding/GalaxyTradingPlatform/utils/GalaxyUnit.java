package com.ding.GalaxyTradingPlatform.utils;

import java.util.HashMap;
import java.util.Map;

public class GalaxyUnit {
	
	private static Map<String, Double> galaxyUnitToCreditMapping = new HashMap<String, Double>();
	
	public static Double getCredits(String name) {
		return galaxyUnitToCreditMapping.get(name);
	}
	
	public static boolean isValidGalaxyUnit(String name) {
		return galaxyUnitToCreditMapping.containsKey(name);
	} 
	
	public static void updateGalaxyUnit(String name, Double credits) {
		if (credits > 0) galaxyUnitToCreditMapping.put(name, credits);
	}
	
}
