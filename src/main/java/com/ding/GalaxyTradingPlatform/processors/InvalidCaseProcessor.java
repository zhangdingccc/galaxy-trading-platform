package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;

class InvalidCaseProcessor extends Processor {
	
	InvalidCaseProcessor(String[] inputTokens) {
		super(inputTokens);
	}
	
	@Override
	public void process() throws InvalidInputException {
		int inputTokensCount = inputTokens.length;
		if (inputTokens[inputTokensCount-1].endsWith("?")) {
			System.out.println("I have no idea what you are talking about");
		} else {
			throw new InvalidInputException("Invalid input format!");
		}
	}
	
}