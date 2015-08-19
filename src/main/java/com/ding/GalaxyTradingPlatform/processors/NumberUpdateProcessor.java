package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.utils.GalaxyNumber;

/*
 * The NumberUpdateProcessor class handles user's input on updating galaxy number system
 */

class NumberUpdateProcessor extends Processor {

	NumberUpdateProcessor(String[] tokens) throws InvalidInputException {
		super(tokens);
	}

	@Override
	public void handleEvent() {
		updateGalaxyNumber();
	}
	
	private void updateGalaxyNumber() {
		String galaxyNumber = tokens[0];
		Character romanUnit = tokens[2].charAt(0);
		GalaxyNumber.updateGalaxyNumber(galaxyNumber, romanUnit);
	}

}
