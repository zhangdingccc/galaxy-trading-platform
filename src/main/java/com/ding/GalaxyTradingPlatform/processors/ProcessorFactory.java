package com.ding.GalaxyTradingPlatform.processors;

import com.ding.GalaxyTradingPlatform.exceptions.InvalidInputException;

/*
 * The ProcessorFactory class gives access to the instances of the Processor's subclasses
 * One user input line is expected from the static factory method
 */

public final class ProcessorFactory {
	
	private ProcessorFactory() {}
	
	private static final String DELIMITER = " ";
	
	private static final String NUMBER_QUERY_PREFIX = "how much is ";
	private static final String UNIT_QUERY_PREFIX = "how many Credits is ";
	
	private static final int NUMBER_UPDATE_COUNT = 3;
	private static final int UNIT_UPDATE_MIN_COUNT = 5;
	
	private static final String UPDATE_VERB = "is";
	private static final String UPDATE_UNIT = "credits";
	
	private static final int NUMBER_UPDATE_VERB_INDEX = 1;
	private static final int NUMBER_UPDATE_ROMAN_INDEX = 2;
	private static final int UNIT_UPDATE_VERB_TAIL_INDEX = 3;
	private static final int UPDATE_UNIT_TAIL_INDEX = 1;
	
	
	public static final Processor createProcessor(String query) throws InvalidInputException {		
		if (query.endsWith("?")) {
			return createQueryProcessor(query);
		} else {
			return createUpdateProcessor(query);
		}
	}
	
	private static final Processor createQueryProcessor(String query) throws InvalidInputException {
		if (isNumberQuery(query)) {
			return new NumberQueryProcessor(query.split(DELIMITER));
		} else if (isUnitQuery(query)) {
			return new UnitQueryProcessor(query.split(DELIMITER));
		} else {
			throw new InvalidInputException("I have no idea what you are talking about");
		}
	}
	
	private static final boolean isNumberQuery(String query) {
		return query.startsWith(NUMBER_QUERY_PREFIX);
	}
	
	private static final boolean isUnitQuery(String query) {
		return query.startsWith(UNIT_QUERY_PREFIX);
	}
	
	private static final Processor createUpdateProcessor(String query) throws InvalidInputException {
		String[] tokens = query.split(DELIMITER);
		
		if (isNumberUpdate(tokens)) {
			return new NumberUpdateProcessor(tokens);
		} else if (isUnitUpdate(tokens)) {
			return new UnitUpdateProcessor(tokens);
		} else {
			throw new InvalidInputException("wrong input format!");
		}
	}
	
	private static final boolean isNumberUpdate(String[] tokens) {
		int tokensCount = tokens.length;
		
		return tokensCount == NUMBER_UPDATE_COUNT &&
				tokens[NUMBER_UPDATE_VERB_INDEX].equalsIgnoreCase(UPDATE_VERB) &&
				tokens[NUMBER_UPDATE_ROMAN_INDEX].length() == 1;
	}
	
	private static final boolean isUnitUpdate(String[] tokens) {
		int tokensCount = tokens.length;
		int unitUpdateVerbIndex = tokensCount-UNIT_UPDATE_VERB_TAIL_INDEX;
		int updateUnitIndex = tokensCount-UPDATE_UNIT_TAIL_INDEX;
		
		return tokensCount >= UNIT_UPDATE_MIN_COUNT &&
				tokens[unitUpdateVerbIndex].equalsIgnoreCase(UPDATE_VERB) &&
				tokens[updateUnitIndex].equalsIgnoreCase(UPDATE_UNIT);		
	}
	
}
