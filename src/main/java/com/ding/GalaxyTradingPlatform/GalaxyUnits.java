package com.ding.GalaxyTradingPlatform;

import java.util.HashMap;

class GalaxyUnits {
	
	private HashMap<String, Double> unitsMap;
	
	public GalaxyUnits() {
		unitsMap = new HashMap<String, Double>();
	}
	
	Double getCredits(String name) {
		return unitsMap.get(name);
	}
	
	boolean hasUnit(String name) {
		return unitsMap.containsKey(name);
	} 
	
	void addUnit(String name, Double credits) {
		if (credits > 0) unitsMap.put(name, credits);
	}
	
}
