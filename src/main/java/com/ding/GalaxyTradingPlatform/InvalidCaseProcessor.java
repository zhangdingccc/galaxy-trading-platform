package com.ding.GalaxyTradingPlatform;

class InvalidCaseProcessor extends Processor {
	
	InvalidCaseProcessor(String[] inputTokens) {
		super(inputTokens);
	}
	
	@Override
	void process() throws InvalidInputException {
		int inputTokensCount = inputTokens.length;
		if (inputTokens[inputTokensCount-1].endsWith("?")) {
			System.out.println("I have no idea what you are talking about");
		} else {
			throw new InvalidInputException("Invalid input format!");
		}
	}
	
}
