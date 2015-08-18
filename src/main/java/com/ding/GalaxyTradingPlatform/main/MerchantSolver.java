package com.ding.GalaxyTradingPlatform.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;
import com.ding.GalaxyTradingPlatform.processors.ProcessorFactory;

/*
 *  The MerchantSolver class provides the entry point to the trading platform
 *  expect input file path specified with args
 */

public class MerchantSolver {
	
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Please specify the input file path!");
			return;
		}
		
		BufferedReader br = null;
		String filePath = args[0];
		try {
			br = new BufferedReader(new FileReader(filePath));
			MerchantSolver solver = new MerchantSolver();
			String query = null;
			while ((query = br.readLine()) != null) {
				try {
					solver.processQuery(query);
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch(FileNotFoundException e) {
			System.err.println("Input file cannot be found!");
		} finally {
			br.close();
		}
	}
	
	void processQuery(String query) throws InvalidInputException {		
		ProcessorFactory.createProcessor(query).handleEvent();
	}
	
}