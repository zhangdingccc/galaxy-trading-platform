package com.ding.GalaxyTradingPlatform;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Unit test for Utils class.
 */
public class UtilsTest
{
	
	@Test
	public void isNonNegativeNumericShouldReturnTrueWhenInputIsNonNegative() {
		assertTrue(Utils.isNonNegativeNumeric("23.0"));
		assertTrue(Utils.isNonNegativeNumeric("57800"));
	}
	
}
