package com.ding.GalaxyTradingPlatform.utils;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.utils.RomanNumeral;

public class RomanNumeralTest {

	@Test(expected = InvalidInputException.class)
	public void romanToDecimalShouldThrowExceptionWhenSameNonRepeatingRomanUnitRepeats() throws InvalidInputException {
		RomanNumeral.romanToDecimal("DD");
	}
	
	@Test(expected = InvalidInputException.class)
	public void romanToDecimalShouldThrowExceptionWhenSameRepeatingRomanUnitRepeatsMoreThanThreeTimesInSuccession() throws InvalidInputException {
		RomanNumeral.romanToDecimal("XXXX");
	}
	
	@Test
	public void romanToDecimalShouldPassWhenNoSameNonRepeatingRomanUnitRepeats() throws InvalidInputException {
		RomanNumeral.romanToDecimal("D");
	}
	
	@Test
	public void romanToDecimalShouldPassWhenSameRepeatingRomanUnitRepeatsLessThanFourTimesInSuccession() throws InvalidInputException {
		RomanNumeral.romanToDecimal("XXX");
	}
	
	@Test(expected = InvalidInputException.class)
	public void romanToDecimalShouldThrowExceptionWhenPrecedingUnitNotSubstractable() throws InvalidInputException {
		RomanNumeral.romanToDecimal("ILIX");
	}
	
	@Test(expected = InvalidInputException.class)
	public void romanToDecimalShouldThrowExceptionWhenNotStartWithLargestValuesInOrder() throws InvalidInputException {
		RomanNumeral.romanToDecimal("VIX");
	}
	
	@Test
	public void romanToDecimalShouldWorkWhenMeetsAllRequirement() throws InvalidInputException {
		assertEquals(10, RomanNumeral.romanToDecimal("X"));
		assertEquals(9, RomanNumeral.romanToDecimal("IX"));
		assertEquals(59, RomanNumeral.romanToDecimal("LIX"));
		assertEquals(13, RomanNumeral.romanToDecimal("IXIV"));
		assertEquals(2006, RomanNumeral.romanToDecimal("MMVI"));
		assertEquals(1944, RomanNumeral.romanToDecimal("MCMXLIV"));
		assertEquals(1903, RomanNumeral.romanToDecimal("MCMIII"));
		assertEquals(2, RomanNumeral.romanToDecimal("II"));
		assertEquals(4, RomanNumeral.romanToDecimal("IV"));
		assertEquals(20, RomanNumeral.romanToDecimal("XX"));
		assertEquals(42, RomanNumeral.romanToDecimal("XLII"));
		assertEquals(13, RomanNumeral.romanToDecimal("IXIV"));
	}

}
