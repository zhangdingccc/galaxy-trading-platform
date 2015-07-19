package com.ding.GalaxyTradingPlatform.exceptions;

/*
 * The InvalidInputException class provides customized exception for invalid input
 * on the trading platform
 */

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {

	public InvalidInputException(String message) {
		super("Invalid Input: " + message);
	}
	
}