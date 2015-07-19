package com.ding.GalaxyTradingPlatform.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;

@SuppressWarnings("serial")
public final class RomanNumeral {
	
	private static final Map<Character, Integer> romanUnitToDecimalMapping = Collections.unmodifiableMap(
			new HashMap<Character, Integer>() {
				{
					put('I', 1);
					put('V', 5);
					put('X', 10);
					put('L', 50);
					put('C', 100);
					put('D', 500);
					put('M', 1000);
				}
			});
	private static Set<Character> nonRepeatingRomanUnits = Collections.unmodifiableSet(
			new HashSet<Character>() {
				{
					add('D');
					add('L');
					add('V');
				}
			});
	private static Set<Character> repeatingRomanUnits = Collections.unmodifiableSet(
			new HashSet<Character>() {
				{
					add('I');
					add('X');
					add('C');
					add('M');
				}
			});
	private static Map<Integer, Integer[]> substractableDecimals = Collections.unmodifiableMap(
			new HashMap<Integer, Integer[]>() {
				{
					put(1, new Integer[] {5, 10});
					put(5, new Integer[] {});
					put(10, new Integer[] {50, 100});
					put(50, new Integer[] {});
					put(100, new Integer[] {500, 1000});
					put(500, new Integer[] {});
					put(1000, new Integer[] {});
				}
			});
	
	public static int romanToDecimal(String roman) throws InvalidInputException {		
		int decimal = 0;
		int lastDecimal = 0;
		int largestDecimalTilNow = 0;
		Map<Character, Integer> unitFreqCount = new HashMap<Character, Integer>();
		
		char[] romanArray = roman.toUpperCase().toCharArray();
		for (int digit = romanArray.length-1; digit >= 0; digit--) {
			Character curRomanUnit = romanArray[digit];
			int curDecimal = RomanNumeral.getDecimalFromRomanUnit(curRomanUnit);
						
			if (curDecimal == lastDecimal) {
				if (RomanNumeral.isNonRepeatingRomanUnit(curRomanUnit)) throw new InvalidInputException("Non repeating roman unit appears more than once in succession.");
				int freq = unitFreqCount.get(curRomanUnit);
				if (RomanNumeral.isRepeatingRomanUnit(curRomanUnit) && freq > 2) throw new InvalidInputException("Repeating roman unit appears more than three times in succession.");
				unitFreqCount.put(curRomanUnit, freq + 1);
			} else {
				unitFreqCount.clear();
				if (curDecimal < lastDecimal) {
					// case 1: invalid, "ILIX" or "VX"
					if (!RomanNumeral.isSubstractable(curDecimal, lastDecimal)) throw new InvalidInputException("Roman unit in front cannot be substracted by the latter one.");
					// case 2: normal, "IX"
					decimal += lastDecimal - curDecimal;
					lastDecimal = 0;
					continue;
				} else {
					unitFreqCount.put(curRomanUnit, 1);
					
					// case 1: invalid, "VIX"
					if (curDecimal < largestDecimalTilNow) throw new InvalidInputException("Only one samll-value roman unit may be substracted from any large-value roman unit.");
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
	
	public static boolean isValidRomanUnit(Character roman) {
		return romanUnitToDecimalMapping.containsKey(roman);
	}
	
	public static int getDecimalFromRomanUnit(Character roman) {
		return romanUnitToDecimalMapping.get(roman);
	}
	
	static boolean isNonRepeatingRomanUnit(Character roman) {
		return nonRepeatingRomanUnits.contains(roman);
	}
	
	static boolean isRepeatingRomanUnit(Character roman) {
		return repeatingRomanUnits.contains(roman);
	}
	
	static boolean isSubstractable(Integer smaller, Integer larger) {
		return Arrays.asList(getSubstractableDecimals(smaller)).contains(larger);
	}
	
	private static Integer[] getSubstractableDecimals(Integer decimal) {
		return substractableDecimals.get(decimal);
	}
	
}