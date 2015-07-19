package com.ding.GalaxyTradingPlatform;

class Utils {

	static boolean isNonNegativeNumeric(String str) {
		return str.matches("\\d+(\\.\\d+)?");
	}

	static String join(String[] arr, String delim, int start, int end) {
		StringBuilder sb = new StringBuilder();
		String loopDelim = "";

		for(int i = start; i < end; i++) {
			sb.append(loopDelim);
			sb.append(arr[i]);            

			loopDelim = delim;
		}
		
		return sb.toString();
	}

	static String formatNumeric(double d) {
		if (d == (long)d)
			return String.format("%d", (long)d);
		else
			return String.format("%s", d);
	}

}