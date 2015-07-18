package com.ding.GalaxyTradingPlatform;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Constants {
	
	private static final Map<Character, Integer> romanUnitToDecimal = Collections.unmodifiableMap(
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
	static boolean isValidRoman(Character roman) {
		return romanUnitToDecimal.containsKey(roman);
	}
	static int getDecimal(Character roman) {
		return romanUnitToDecimal.get(roman);
	}
	
	private static Set<Character> nonRepeatingRomanUnit = Collections.unmodifiableSet(
			new HashSet<Character>() {
				{
					add('D');
					add('L');
					add('V');
				}
			});
	static boolean isNonRepeatingRomanUnit(Character roman) {
		return nonRepeatingRomanUnit.contains(roman);
	}
	
	private static Set<Character> repeatingRomanUnit = Collections.unmodifiableSet(
			new HashSet<Character>() {
				{
					add('I');
					add('X');
					add('C');
					add('M');
				}
			});
	static boolean isRepeatingRomanUnit(Character roman) {
		return repeatingRomanUnit.contains(roman);
	}

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
	static boolean isSubstractable(Integer smaller, Integer larger) {
		return Arrays.asList(getSubstractableDecimals(smaller)).contains(larger);
	}
	private static Integer[] getSubstractableDecimals(Integer decimal) {
		return substractableDecimals.get(decimal);
	}
	
}