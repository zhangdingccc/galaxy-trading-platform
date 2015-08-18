package com.ding.GalaxyTradingPlatform.processors;

import java.util.Arrays;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.utils.GalaxyNumber;
import com.ding.GalaxyTradingPlatform.utils.GalaxyUnit;
import com.ding.GalaxyTradingPlatform.utils.RomanNumeral;
import com.ding.GalaxyTradingPlatform.utils.Utils;

/*
 * The UnitUpdateProcessor class handles user's input on updating galaxy unit credits
 */

class UnitUpdateProcessor extends Processor {

	UnitUpdateProcessor(String[] inputTokens) {
		super(inputTokens);
	}

	@Override
	public void handleEvent() throws NumberFormatException, InvalidInputException {
		updateGalaxyUnits();
	}
	
	private void updateGalaxyUnits() throws NumberFormatException, InvalidInputException {
		int inputTokensCount = inputTokens.length;
		String[] arr = Arrays.copyOfRange(inputTokens, 0, inputTokensCount - 3);
		String credits = inputTokens[inputTokensCount-2];
		
		// ignore invalid input line, eg: "glob Gold is X Credits"
		if (!Utils.isNonNegativeNumeric(credits)) return;
		
		// get roman
		StringBuilder romanBuilder = new StringBuilder();
		int index;
		for (index = 0; index < arr.length; index++) {
			if (!GalaxyNumber.isValidGalaxyNumber(arr[index])) break;
			romanBuilder.append(GalaxyNumber.getRomanUnit(arr[index]));
		}
		
		// ignore invalid input line, eg: "Gold Gold is 100 Credits" or "glob glob is 100 Credits"
		if (index == 0 || index == arr.length) return;
		
		// calculate unit credit
		String unitName = Utils.join(arr, " ", index, arr.length);
		Double unitCredit = Double.parseDouble(credits) / RomanNumeral.romanToDecimal(romanBuilder.toString());		
		GalaxyUnit.updateGalaxyUnit(unitName, unitCredit);
	}
	
}
