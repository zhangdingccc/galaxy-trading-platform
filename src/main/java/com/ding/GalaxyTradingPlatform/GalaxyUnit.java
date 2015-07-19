package com.ding.GalaxyTradingPlatform;

import java.util.HashMap;
import java.util.Map;

class GalaxyUnit {
	
	private static Map<String, Double> galaxyUnitToCreditMapping = new HashMap<String, Double>();
	
	static Double getCredits(String name) {
		return galaxyUnitToCreditMapping.get(name);
	}
	
	static boolean isValidGalaxyUnit(String name) {
		return galaxyUnitToCreditMapping.containsKey(name);
	} 
	
	static void updateGalaxyUnit(String name, Double credits) {
		if (credits > 0) galaxyUnitToCreditMapping.put(name, credits);
	}
	
}
