package com.ding.GalaxyTradingPlatform;

import java.util.Arrays;

class Trader {
	
	private final int NUMBER_TO_DECIMAL_QUERY = 1;
	private final int UNITS_CREDIT_QUERY = 2;
	
	private GalaxyNumbers numbers;
	private GalaxyUnits units;
	
	Trader() {
		numbers = new GalaxyNumbers();
		units = new GalaxyUnits();
	}
	
	void trade(String inputLine) {		
		if (inputLine.endsWith("?")) {
			answerUserQuery(inputLine);
		} else if (inputLine.contains("is")) {
			updateTrader(inputLine);
		} else {
			// ignore invalid input line, eg: "hello world"
		}
		return;
	}
	
	private void updateTrader(String inputLine) {
		String[] inputLineTokens = inputLine.split(" ");
		int inputLineTokensCount = inputLineTokens.length;
		
		if (inputLineTokensCount == 3 && inputLineTokens[1].equalsIgnoreCase("is") && inputLineTokens[2].length() == 1) {
			updateGalaxyNumbers(inputLineTokens[0], inputLineTokens[2].charAt(0));
		} else if (inputLineTokensCount > 4 && inputLineTokens[inputLineTokensCount-1].equalsIgnoreCase("credits") && inputLineTokens[inputLineTokensCount-3].equalsIgnoreCase("is")) {
			updateGalaxyUnits(Arrays.copyOfRange(inputLineTokens, 0, inputLineTokensCount - 3), inputLineTokens[inputLineTokensCount-2]);
		} else {
			// ignore invalid input line, eg: "glob glob is V"
		}
		return;
	}
	
	private void updateGalaxyNumbers(String name, Character romanUnit) {
		numbers.addNumber(name, romanUnit);
	}
	
	private void updateGalaxyUnits(String[] arr, String credits) {
		// ignore invalid input line, eg: "glob Gold is X Credits"
		if (!Utils.isNonNegativeNumeric(credits)) return;
		
		// get roman
		StringBuilder romanBuilder = new StringBuilder();
		int index;
		for (index = 0; index < arr.length; index++) {
			if (!numbers.hasNumber(arr[index])) break;
			romanBuilder.append(numbers.getRoman(arr[index]));
		}
		
		// ignore invalid input line, eg: "Gold Gold is 100 Credits" or "glob glob is 100 Credits"
		if (index == 0 || index == arr.length) return;
		
		// calculate unit credit
		String unitName = getGalaxyUnitName(arr, index, arr.length);
		Double unitCredit = Double.parseDouble(credits) / Utils.romanToDecimal(romanBuilder.toString());		
		units.addUnit(unitName, unitCredit);
	}
	
	private void answerUserQuery(String query) {
		if (query.startsWith("how much is ")) {
			answerGalaxyNumberToDecimalQuery(query);
		} else if (query.startsWith("how many Credits is ")) {
			answerGalaxyUnitsCreditQuery(query);
		} else {
			System.out.println("I have no idea what you are talking about");
		}
	}
	
	private void answerGalaxyNumberToDecimalQuery(String query) {
		String[] queryTokens = query.split(" ");
		int queryTokensCount = queryTokens.length;
		
		// ignore invalid input
		if (queryTokensCount < 5 || !queryTokens[queryTokensCount-1].equals("?")) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// get roman
		StringBuilder romanBuilder = new StringBuilder();
		int index = 3;
		for (; index < queryTokensCount-1; index++) {
			if (!numbers.hasNumber(queryTokens[index])) break;
			romanBuilder.append(numbers.getRoman(queryTokens[index]));
		}
		
		// ignore invalid input line
		if (index == 0 || index < queryTokensCount-1) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// calculate roman number to decimal
		int result = Utils.romanToDecimal(romanBuilder.toString());
		
		printResult(query, NUMBER_TO_DECIMAL_QUERY, result);
	}
	
	private void answerGalaxyUnitsCreditQuery(String query) {
		String[] queryTokens = query.split(" ");
		int queryTokensCount = queryTokens.length;
		
		// ignore invalid input line
		if (queryTokensCount < 7 || !queryTokens[queryTokensCount-1].equals("?")) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// get roman
		StringBuilder romanBuilder = new StringBuilder();
		int index = 4;
		for (; index < queryTokensCount-1; index++) {
			if (!numbers.hasNumber(queryTokens[index])) break;
			romanBuilder.append(numbers.getRoman(queryTokens[index]));
		}
		
		// ignore invalid input line
		if (index == 0 || index == queryTokensCount-1) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// get unit name
		String unitName = getGalaxyUnitName(queryTokens, index, queryTokensCount - 1);
		
		// ignore invalid input
		if (!units.hasUnit(unitName)) {
			System.out.println("I have no idea what you are talking about");
			return;
		}
		
		// calculate unit credit
		int unitCount = Utils.romanToDecimal(romanBuilder.toString());
		double unitCredit = units.getCredits(unitName);
		double totalCredit = unitCredit * unitCount;
		
		printResult(query, UNITS_CREDIT_QUERY, totalCredit);
	}
	
	private String getGalaxyUnitName(String[] inputTokens, int start, int end) {
		StringBuilder unitNameBuilder = new StringBuilder();
		unitNameBuilder.append(inputTokens[start++]);
		for (int cur = start; cur < end; cur++) {
			unitNameBuilder.append(" ");
			unitNameBuilder.append(inputTokens[cur]);
		}
		return unitNameBuilder.toString();
	}
	
	private void printResult(String query, int queryType, double result) {
		StringBuilder answerBuilder = new StringBuilder();
		
		if (queryType == NUMBER_TO_DECIMAL_QUERY) {
			answerBuilder.append(query.substring(12, query.length()-2));
			answerBuilder.append(" is ");
			answerBuilder.append(Utils.formatNumeric(result));
		} else if (queryType == UNITS_CREDIT_QUERY) {
			answerBuilder.append(query.substring(20, query.length()-2));
			answerBuilder.append(" is ");
			answerBuilder.append(Utils.formatNumeric(result));
			answerBuilder.append(" Credits");
		} else {
			// for scale use
		}
		
		System.out.println(answerBuilder.toString());
	}
	
}