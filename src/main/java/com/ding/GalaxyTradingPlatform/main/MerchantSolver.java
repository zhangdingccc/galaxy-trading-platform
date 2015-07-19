package com.ding.GalaxyTradingPlatform.main;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.processors.Processors;

class MerchantSolver {
	
	void trade(String inputLine) throws InvalidInputException {		
		Processors.getProcessor(inputLine).process();
	}
	
}