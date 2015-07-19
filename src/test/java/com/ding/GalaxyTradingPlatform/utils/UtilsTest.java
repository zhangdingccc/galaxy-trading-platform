package com.ding.GalaxyTradingPlatform.utils;

import static org.junit.Assert.*;
import org.junit.Test;

import com.ding.GalaxyTradingPlatform.utils.Utils;


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
