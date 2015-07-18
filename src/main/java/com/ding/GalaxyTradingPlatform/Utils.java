package com.ding.GalaxyTradingPlatform;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

class Utils {
	
	static int romanToDecimal(String roman) {		
		int decimal = 0;
		int lastDecimal = 0;
		int largestDecimalTilNow = 0;
		Map<Character, Integer> unitFreqCount = new HashMap<Character, Integer>();
		
		char[] romanArray = roman.toUpperCase().toCharArray();
		for (int digit = romanArray.length-1; digit >= 0; digit--) {
			Character curRomanUnit = romanArray[digit];
			int curDecimal = Constants.getDecimal(curRomanUnit);
						
			if (curDecimal == lastDecimal) {
				if (Constants.isNonRepeatingRomanUnit(curRomanUnit)) throw new RuntimeErrorException(null);
				int freq = unitFreqCount.get(curRomanUnit);
				if (Constants.isRepeatingRomanUnit(curRomanUnit) && freq > 2) throw new RuntimeErrorException(null);
				unitFreqCount.put(curRomanUnit, freq + 1);
			} else {
				unitFreqCount.clear();
				if (curDecimal < lastDecimal) {
					// case 1: invalid, "ILIX" or "VX"
					if (!Constants.isSubstractable(curDecimal, lastDecimal)) throw new RuntimeErrorException(null);
					// case 2: normal, "IX"
					decimal += lastDecimal - curDecimal;
					lastDecimal = 0;
					continue;
				} else {
					unitFreqCount.put(curRomanUnit, 1);
					
					// case 1: invalid, "VIX"
					if (curDecimal < largestDecimalTilNow) throw new RuntimeErrorException(null);
					// case 2: normal, "LIX"
					largestDecimalTilNow = curDecimal;
				}
			}
			
			decimal += lastDecimal;
			lastDecimal = curDecimal;
		}
		
		decimal += lastDecimal;
		
		return decimal;
	}
	
	static boolean isNonNegativeNumeric(String str) {
		return str.matches("\\d+(\\.\\d+)?");
	}
	
	static String formatNumeric(double d) {
		if (d == (long)d)
			return String.format("%d", (long)d);
		else
			return String.format("%s", d);
	}
	
}