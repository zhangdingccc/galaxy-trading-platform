package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;

/*
 * The ProcessorFactory class gives access to the instances of the Processor's subclasses
 * One user input line is expected from the static factory method
 */

public final class ProcessorFactory {
	
	private ProcessorFactory() {}
	
	public static final Processor createProcessor(String query) throws InvalidInputException {
		String[] inputTokens = query.split(" ");
		int inputTokensCount = inputTokens.length;
		
		if (inputTokens[inputTokensCount-1].equals("?")) {
			if (query.startsWith("how much is ")) {
				return new NumberQueryProcessor(inputTokens);
			} else if (query.startsWith("how many Credits is ")) {
				return new UnitQueryProcessor(inputTokens);
			} else {
				throw new InvalidInputException("I have no idea what you are talking about");
			}
		} else {
			if (inputTokensCount == 3 && inputTokens[1].equalsIgnoreCase("is") && inputTokens[2].length() == 1) {
				return new NumberUpdateProcessor(inputTokens);
			} else if (inputTokensCount > 4 && inputTokens[inputTokensCount-1].equalsIgnoreCase("credits") && inputTokens[inputTokensCount-3].equalsIgnoreCase("is")) {
				return new UnitUpdateProcessor(inputTokens);
			} else {
				throw new InvalidInputException("wrong input format!");
			}
		}
	}
	
}
