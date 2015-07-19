package com.ding.GalaxyTradingPlatform;

class MerchantSolver {
	
	void trade(String inputLine) throws InvalidInputException {		
		Processors.getProcessor(inputLine).process();
	}
	
}