package com.ding.GalaxyTradingPlatform.exceptions;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {

	public InvalidInputException(String message) {
		super("Invalid Input: " + message);
	}
	
}