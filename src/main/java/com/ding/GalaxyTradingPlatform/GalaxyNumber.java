package com.ding.GalaxyTradingPlatform;

import java.util.HashMap;
import java.util.Map;

class GalaxyNumber {
	
	private static Map<String, Character> galaxyNumberToRomanUnitMapping = new HashMap<String, Character>();
	
	static Character getRomanUnit(String name) {
		return galaxyNumberToRomanUnitMapping.get(name);
	}
	
	static boolean isValidGalaxyNumber(String name) {
		return galaxyNumberToRomanUnitMapping.containsKey(name);
	}
	
	static void updateGalaxyNumber(String name, Character roman) {
		if (RomanNumeral.isValidRomanUnit(roman)) galaxyNumberToRomanUnitMapping.put(name, roman);
	}
	
}