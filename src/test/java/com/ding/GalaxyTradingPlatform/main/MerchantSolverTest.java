package com.ding.GalaxyTradingPlatform.main;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.main.MerchantSolver;

/**
 * Unit test for Trader class.
 */
public class MerchantSolverTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private MerchantSolver solver;
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		solver = new MerchantSolver();
		
	}
	
	@After
	public void tearDown() {
		System.setOut(null);
		solver = null;
	}

	@Test(expected = InvalidInputException.class)
	public void tradeShouldMatchSampleOutputWhenSampleInputIsGiven() throws InvalidInputException {
		solver.processQuery("glob is I");
		solver.processQuery("prok is V");
		solver.processQuery("pish is X");
		solver.processQuery("tegj is L");
		solver.processQuery("glob glob Silver is 34 Credits");
		solver.processQuery("glob prok Gold is 57800 Credits");
		solver.processQuery("pish pish Iron is 3910 Credits");
		solver.processQuery("how much is pish tegj glob glob ?");
		solver.processQuery("how many Credits is glob prok Silver ?");
		solver.processQuery("how many Credits is glob prok Gold ?");
		solver.processQuery("how many Credits is glob prok Iron ?");
		solver.processQuery("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		
		StringBuilder expectedBuilder = new StringBuilder();
		expectedBuilder.append("pish tegj glob glob is 42\n");
		expectedBuilder.append("glob prok Silver is 68 Credits\n");
		expectedBuilder.append("glob prok Gold is 57800 Credits\n");
		expectedBuilder.append("glob prok Iron is 782 Credits\n");
		//expectedBuilder.append("I have no idea what you are talking about\n");
		
		assertEquals(expectedBuilder.toString(), outContent.toString());
	}

}