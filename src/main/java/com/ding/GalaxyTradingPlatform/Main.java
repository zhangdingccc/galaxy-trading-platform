package com.ding.GalaxyTradingPlatform;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 *  Main : Entry point to Solver
 *  Expect input file path passed with args
 */


public class Main {
	
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Please specify the input file path!");
			return;
		}
		
		String filePath = args[0];
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			MerchantSolver trader = new MerchantSolver();
			String query = null;
			while ((query = br.readLine()) != null) {
				trader.trade(query);
			}
			br.close();
		} catch(FileNotFoundException e) {
			System.out.println("Input file cannot be found!");
		} catch (InvalidInputException e) {
			System.err.println(e.getMessage());
		}		
	}
	
}
