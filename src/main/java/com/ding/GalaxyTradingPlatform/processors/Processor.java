package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;

/*
 * The Processor abstract class defines the basic structure of all processors
 */

public abstract class Processor {
	
	String[] tokens;
	
	Processor(String[] tokens) {
		this.tokens = tokens;
	}
	
	public void handleEvent() throws InvalidInputException {}
	
}