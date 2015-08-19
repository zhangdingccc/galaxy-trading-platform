package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.utils.GalaxyNumber;
import com.ding.GalaxyTradingPlatform.utils.GalaxyUnit;
import com.ding.GalaxyTradingPlatform.utils.RomanNumeral;
import com.ding.GalaxyTradingPlatform.utils.Utils;

/*
 * The UnitQueryProcessor class handles user's query on galaxy unit credits
 */

class UnitQueryProcessor extends Processor {
	
	UnitQueryProcessor(String[] tokens) {
		super(tokens);
	}

	@Override
	public void handleEvent() throws InvalidInputException {
		answerGalaxyUnitsCreditQuery();
	}
	
	private void answerGalaxyUnitsCreditQuery() throws InvalidInputException {
		int tokensCount = tokens.length;
		
		// ignore invalid input line
		if (tokensCount < 7 || !tokens[tokensCount-1].equals("?")) {
			throw new InvalidInputException("I have no idea what you are talking about");
		}
		
		// get roman
		StringBuilder romanBuilder = new StringBuilder();
		int index = 4;
		for (; index < tokensCount-1; index++) {
			if (!GalaxyNumber.isValidGalaxyNumber(tokens[index])) break;
			romanBuilder.append(GalaxyNumber.getRomanUnit(tokens[index]));
		}
		
		// ignore invalid input line
		if (index == 0 || index == tokensCount-1) {
			throw new InvalidInputException("I have no idea what you are talking about");
		}
		
		// get unit name
		String unitName = Utils.join(tokens, " ", index, tokensCount - 1);
		
		// ignore invalid input
		if (!GalaxyUnit.isValidGalaxyUnit(unitName)) {
			throw new InvalidInputException("I have no idea what you are talking about");
		}
		
		// calculate unit credit
		int unitCount = RomanNumeral.romanToDecimal(romanBuilder.toString());
		double unitCredit = GalaxyUnit.getCredits(unitName);
		double totalCredit = unitCredit * unitCount;
		
		printResult(Utils.join(tokens, " ", 0, tokensCount), totalCredit);
	}
	
	private void printResult(String query, double result) {
		StringBuilder answerBuilder = new StringBuilder();
		
		answerBuilder.append(query.substring(20, query.length()-2));
		answerBuilder.append(" is ");
		answerBuilder.append(Utils.formatNumeric(result));
		answerBuilder.append(" Credits");
		
		System.out.println(answerBuilder.toString());
	}

}