package com.ding.GalaxyTradingPlatform;

class UnitQueryProcessor extends Processor {
	
	UnitQueryProcessor(String[] inputTokens) {
		super(inputTokens);
	}

	@Override
	void process() throws InvalidInputException {
		answerGalaxyUnitsCreditQuery();
	}
	
	private void answerGalaxyUnitsCreditQuery() throws InvalidInputException {
		int inputTokensCount = inputTokens.length;
		
		// ignore invalid input line
		if (inputTokensCount < 7 || !inputTokens[inputTokensCount-1].equals("?")) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// get roman
		StringBuilder romanBuilder = new StringBuilder();
		int index = 4;
		for (; index < inputTokensCount-1; index++) {
			if (!GalaxyNumber.isValidGalaxyNumber(inputTokens[index])) break;
			romanBuilder.append(GalaxyNumber.getRomanUnit(inputTokens[index]));
		}
		
		// ignore invalid input line
		if (index == 0 || index == inputTokensCount-1) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// get unit name
		String unitName = Utils.join(inputTokens, " ", index, inputTokensCount - 1);
		
		// ignore invalid input
		if (!GalaxyUnit.isValidGalaxyUnit(unitName)) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// calculate unit credit
		int unitCount = RomanNumeral.romanToDecimal(romanBuilder.toString());
		double unitCredit = GalaxyUnit.getCredits(unitName);
		double totalCredit = unitCredit * unitCount;
		
		printResult(Utils.join(inputTokens, " ", 0, inputTokensCount), totalCredit);
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