package com.ding.GalaxyTradingPlatform;

class NumberUpdateProcessor extends Processor {

	NumberUpdateProcessor(String[] inputTokens) throws InvalidInputException {
		super(inputTokens);
	}

	@Override
	void process() {
		updateGalaxyNumber();
	}
	
	private void updateGalaxyNumber() {
		String galaxyNumber = inputTokens[0];
		Character romanUnit = inputTokens[2].charAt(0);
		GalaxyNumber.updateGalaxyNumber(galaxyNumber, romanUnit);
	}

}
