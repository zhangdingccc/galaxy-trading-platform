package com.ding.GalaxyTradingPlatform;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {

	public InvalidInputException(String message) {
		super("Invalid Input: " + message);
	}
	
}