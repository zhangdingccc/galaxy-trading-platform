package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.utils.GalaxyNumber;
import com.ding.GalaxyTradingPlatform.utils.RomanNumeral;
import com.ding.GalaxyTradingPlatform.utils.Utils;

/*
 * The NumberQueryProcessor class handles user's query on galaxy number converting to decimal
 */

class NumberQueryProcessor extends Processor {

	NumberQueryProcessor(String[] tokens) {
		super(tokens);
	}

	@Override
	public void handleEvent() throws InvalidInputException {
		answerGalaxyNumberToDecimalQuery();
	}
	
	private void answerGalaxyNumberToDecimalQuery() throws InvalidInputException {
		int tokensCount = tokens.length;
		
		// invalid query
		if (tokensCount < 5 || !tokens[tokensCount-1].equals("?")) {
			throw new InvalidInputException("I have no idea what you are talking about");
		}
		
		// get roman
		StringBuilder romanBuilder = new StringBuilder();
		int index = 3;
		for (; index < tokensCount-1; index++) {
			if (!GalaxyNumber.isValidGalaxyNumber(tokens[index])) break;
			romanBuilder.append(GalaxyNumber.getRomanUnit(tokens[index]));
		}
		
		// invalid query
		if (index == 0 || index < tokensCount-1) {
			throw new InvalidInputException("I have no idea what you are talking about");
		}
		
		// calculate roman number to decimal
		int result = RomanNumeral.romanToDecimal(romanBuilder.toString());
		
		printResult(Utils.join(tokens, " ", 0, tokensCount), result);
	}
	
	private void printResult(String query, double result) {
		StringBuilder answerBuilder = new StringBuilder();
		
		answerBuilder.append(query.substring(12, query.length()-2));
		answerBuilder.append(" is ");
		answerBuilder.append(Utils.formatNumeric(result));
		
		System.out.println(answerBuilder.toString());
	}
	
}