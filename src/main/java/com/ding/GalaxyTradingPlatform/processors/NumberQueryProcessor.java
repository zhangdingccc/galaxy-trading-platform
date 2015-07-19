package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.utils.GalaxyNumber;
import com.ding.GalaxyTradingPlatform.utils.RomanNumeral;
import com.ding.GalaxyTradingPlatform.utils.Utils;

class NumberQueryProcessor extends Processor {

	NumberQueryProcessor(String[] inputTokens) {
		super(inputTokens);
	}

	@Override
	public void process() throws InvalidInputException {
		answerGalaxyNumberToDecimalQuery();
	}
	
	private void answerGalaxyNumberToDecimalQuery() throws InvalidInputException {
		int inputTokensCount = inputTokens.length;
		
		// ignore invalid input
		if (inputTokensCount < 5 || !inputTokens[inputTokensCount-1].equals("?")) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// get roman
		StringBuilder romanBuilder = new StringBuilder();
		int index = 3;
		for (; index < inputTokensCount-1; index++) {
			if (!GalaxyNumber.isValidGalaxyNumber(inputTokens[index])) break;
			romanBuilder.append(GalaxyNumber.getRomanUnit(inputTokens[index]));
		}
		
		// ignore invalid input line
		if (index == 0 || index < inputTokensCount-1) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// calculate roman number to decimal
		int result = RomanNumeral.romanToDecimal(romanBuilder.toString());
		
		printResult(Utils.join(inputTokens, " ", 0, inputTokensCount), result);
	}
	
	private void printResult(String query, double result) {
		StringBuilder answerBuilder = new StringBuilder();
		
		answerBuilder.append(query.substring(12, query.length()-2));
		answerBuilder.append(" is ");
		answerBuilder.append(Utils.formatNumeric(result));
		
		System.out.println(answerBuilder.toString());
	}
	
}