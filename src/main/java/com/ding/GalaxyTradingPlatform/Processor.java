package com.ding.GalaxyTradingPlatform;

abstract class Processor {
	
	String[] inputTokens;
	
	Processor(String[] inputTokens) {
		this.inputTokens = inputTokens;
	}
	
	void process() throws InvalidInputException {}
	
}