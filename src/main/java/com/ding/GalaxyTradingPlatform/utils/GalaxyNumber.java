package com.ding.GalaxyTradingPlatform.utils;

import java.util.HashMap;
import java.util.Map;

/*
 * The GalaxyNumber class maintains the mapping between galaxy number and roman unit
 * and provides basic utils method on galaxy number
 */

public class GalaxyNumber {
	
	private static Map<String, Character> galaxyNumberToRomanUnitMapping = new HashMap<String, Character>();
	
	public static Character getRomanUnit(String name) {
		return galaxyNumberToRomanUnitMapping.get(name);
	}
	
	public static boolean isValidGalaxyNumber(String name) {
		return galaxyNumberToRomanUnitMapping.containsKey(name);
	}
	
	public static void updateGalaxyNumber(String name, Character roman) {
		if (RomanNumeral.isValidRomanUnit(roman)) galaxyNumberToRomanUnitMapping.put(name, roman);
	}
	
}