package com.ding.GalaxyTradingPlatform;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Unit test for Utils class.
 */
public class UtilsTest
{
    
	@Test(expected = RuntimeException.class)
	public void romanToDecimalShouldThrowExceptionWhenSameNonRepeatingRomanUnitRepeats() {
		Utils.romanToDecimal("DD");
	}
	
	@Test(expected = RuntimeException.class)
	public void romanToDecimalShouldThrowExceptionWhenSameRepeatingRomanUnitRepeatsMoreThanThreeTimesInSuccession() {
		Utils.romanToDecimal("XXXX");
	}
	
	@Test
	public void romanToDecimalShouldPassWhenNoSameNonRepeatingRomanUnitRepeats() {
		Utils.romanToDecimal("D");
		assertTrue(true);
	}
	
	@Test
	public void romanToDecimalShouldPassWhenSameRepeatingRomanUnitRepeatsLessThanFourTimesInSuccession() {
		Utils.romanToDecimal("XXX");
		assertTrue(true);
	}
	
	@Test(expected = RuntimeException.class)
	public void romanToDecimalShouldThrowExceptionWhenPrecedingUnitNotSubstractable() {
		Utils.romanToDecimal("ILIX");
	}
	
	@Test(expected = RuntimeException.class)
	public void romanToDecimalShouldThrowExceptionWhenNotStartWithLargestValuesInOrder() {
		Utils.romanToDecimal("VIX");
	}
	
	@Test
	public void romanToDecimalShouldWorkWhenMeetsAllRequirement() {
		assertEquals(10, Utils.romanToDecimal("X"));
		assertEquals(9, Utils.romanToDecimal("IX"));
		assertEquals(59, Utils.romanToDecimal("LIX"));
		assertEquals(13, Utils.romanToDecimal("IXIV"));
		assertEquals(2006, Utils.romanToDecimal("MMVI"));
		assertEquals(1944, Utils.romanToDecimal("MCMXLIV"));
		assertEquals(1903, Utils.romanToDecimal("MCMIII"));
		assertEquals(2, Utils.romanToDecimal("II"));
		assertEquals(4, Utils.romanToDecimal("IV"));
		assertEquals(20, Utils.romanToDecimal("XX"));
		assertEquals(42, Utils.romanToDecimal("XLII"));
		assertEquals(13, Utils.romanToDecimal("IXIV"));
	}
	
	@Test
	public void isNonNegativeNumericShouldReturnTrueWhenInputIsNonNegative() {
		assertTrue(Utils.isNonNegativeNumeric("23.0"));
		assertTrue(Utils.isNonNegativeNumeric("57800"));
	}
}
