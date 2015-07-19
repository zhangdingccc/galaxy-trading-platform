package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.utils.GalaxyNumber;

/*
 * The NumberUpdateProcessor class handles user's input on updating galaxy number system
 */

class NumberUpdateProcessor extends Processor {

	NumberUpdateProcessor(String[] inputTokens) throws InvalidInputException {
		super(inputTokens);
	}

	@Override
	public void process() {
		updateGalaxyNumber();
	}
	
	private void updateGalaxyNumber() {
		String galaxyNumber = inputTokens[0];
		Character romanUnit = inputTokens[2].charAt(0);
		GalaxyNumber.updateGalaxyNumber(galaxyNumber, romanUnit);
	}

}
