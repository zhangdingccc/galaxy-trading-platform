package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;

public abstract class Processor {
	
	String[] inputTokens;
	
	Processor(String[] inputTokens) {
		this.inputTokens = inputTokens;
	}
	
	public void process() throws InvalidInputException {}
	
}