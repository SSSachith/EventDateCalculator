package com.loreca.eventplanner;

public interface InputValidator {

	/*
	 * This method will run a validation criteria from most general to most specific following a process of elimination
	 * 
	 * */
	public boolean validateInput(final String input1, final String input2);
	
	public boolean validateInputPattern(final String input);
	
	public boolean validateInputYearRange(final String input);
	
	public boolean validateInputMonthRange(final String input);
	
	public boolean validateInputDaysOfMonth(final String inputDay, final String inputMonth, final String inputYear);
}
