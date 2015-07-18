package com.ding.GalaxyTradingPlatform;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Trader class.
 */
public class TraderTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private Trader trader;
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		trader = new Trader();
		
	}
	
	@After
	public void tearDown() {
		System.setOut(null);
		trader = null;
	}

	@Test
	public void tradeShouldMatchSampleOutputWhenSampleInputIsGiven() {
		trader.trade("glob is I");
		trader.trade("prok is V");
		trader.trade("pish is X");
		trader.trade("tegj is L");
		trader.trade("glob glob Silver is 34 Credits");
		trader.trade("glob prok Gold is 57800 Credits");
		trader.trade("pish pish Iron is 3910 Credits");
		trader.trade("how much is pish tegj glob glob ?");
		trader.trade("how many Credits is glob prok Silver ?");
		trader.trade("how many Credits is glob prok Gold ?");
		trader.trade("how many Credits is glob prok Iron ?");
		trader.trade("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		
		StringBuilder expectedBuilder = new StringBuilder();
		expectedBuilder.append("pish tegj glob glob is 42\n");
		expectedBuilder.append("glob prok Silver is 68 Credits\n");
		expectedBuilder.append("glob prok Gold is 57800 Credits\n");
		expectedBuilder.append("glob prok Iron is 782 Credits\n");
		expectedBuilder.append("I have no idea what you are talking about\n");
		
		assertEquals(expectedBuilder.toString(), outContent.toString());
	}

}