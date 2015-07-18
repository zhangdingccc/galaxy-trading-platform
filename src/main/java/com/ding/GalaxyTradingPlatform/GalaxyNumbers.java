package com.ding.GalaxyTradingPlatform;

import java.util.HashMap;
import java.util.Map;

class GalaxyNumbers {
	
	private Map<String, Character> numbersMap;
	
	GalaxyNumbers() {
		this.numbersMap = new HashMap<String, Character>();
	}
	
	Character getRoman(String name) {
		return numbersMap.get(name);
	}
	
	boolean hasNumber(String name) {
		return numbersMap.containsKey(name);
	}
	
	void addNumber(String name, Character roman) {
		if (Constants.isValidRoman(roman)) numbersMap.put(name, roman);
	}
	
}