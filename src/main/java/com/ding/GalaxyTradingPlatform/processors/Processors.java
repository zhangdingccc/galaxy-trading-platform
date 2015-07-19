package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;

public final class Processors {
	private Processors() {}
	
	public static final Processor getProcessor(String input) throws InvalidInputException {
		String[] inputTokens = input.split(" ");
		int inputTokensCount = inputTokens.length;
		
		if (inputTokens[inputTokensCount-1].equals("?")) {
			if (input.startsWith("how much is ")) {
				return new NumberQueryProcessor(inputTokens);
			} else if (input.startsWith("how many Credits is ")) {
				return new UnitQueryProcessor(inputTokens);
			} else {
				return new InvalidCaseProcessor(inputTokens);
			}
		} else {
			if (inputTokensCount == 3 && inputTokens[1].equalsIgnoreCase("is") && inputTokens[2].length() == 1) {
				return new NumberUpdateProcessor(inputTokens);
			} else if (inputTokensCount > 4 && inputTokens[inputTokensCount-1].equalsIgnoreCase("credits") && inputTokens[inputTokensCount-3].equalsIgnoreCase("is")) {
				return new UnitUpdateProcessor(inputTokens);
			} else if (input.endsWith("?")) {
				return new InvalidCaseProcessor(inputTokens);
			} else {
				// ignore invalid input line, eg: "glob glob is V"
			}
		}
		return null;
	}
	
}
