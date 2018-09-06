package com.loreca.eventplanner.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.loreca.eventplanner.DateCalculator;
import com.loreca.eventplanner.DateCalculatorImpl;
import com.loreca.eventplanner.InputValidator;
import com.loreca.eventplanner.InputValidatorImpl;

public class InputValidatorTest {

	static DateCalculator dateCalculator;
	static InputValidator inputValidator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dateCalculator = new DateCalculatorImpl();
		inputValidator = new InputValidatorImpl();
	}

	@Test
	public void dateShouldMatchTheRightPattern() {
		assert (inputValidator.validateInputPattern("01/01/2001"));
		assert (inputValidator.validateInputPattern("01/12/2001"));
		assert (inputValidator.validateInputPattern("11/12/2001"));
	}

	@Test
	public void dateMustNotHaveWrongPatterns() {
		assertFalse(inputValidator.validateInputPattern("1/1/2001"));
		assertFalse(inputValidator.validateInputPattern("1-1-2001"));
		assertFalse(inputValidator.validateInputPattern("1/1/01"));
		assertFalse(inputValidator.validateInputPattern("1-JAN-01"));
	}

	@Test
	public void yearsShouldBeInValidRange() {
		assert (inputValidator.validateInputYearRange("2004"));
		// Edge cases
		assert (inputValidator.validateInputYearRange("1901"));
		assert (inputValidator.validateInputYearRange("2999"));
	}

	@Test
	public void yearsShouldNotBeOutOfValidRange() {
		assertFalse(inputValidator.validateInputYearRange("3500"));
		assertFalse(inputValidator.validateInputYearRange("1550"));
		// Edge cases
		assertFalse(inputValidator.validateInputYearRange("1900"));
		assertFalse(inputValidator.validateInputYearRange("3000"));
	}

	@Test
	public void monthsShouldBeInValidRange() {
		assert (inputValidator.validateInputMonthRange("05"));
		// Edge cases
		assert (inputValidator.validateInputMonthRange("01"));
		assert (inputValidator.validateInputMonthRange("12"));
	}

	@Test
	public void monthsShouldNotBeOutOfValidRange() {
		assertFalse(inputValidator.validateInputMonthRange("35"));
		assertFalse(inputValidator.validateInputMonthRange("55"));
		// Edge cases
		assertFalse(inputValidator.validateInputMonthRange("00"));
		assertFalse(inputValidator.validateInputMonthRange("13"));
	}

	@Test
	public void daysOfMonthShouldBeInValidRange() {
		assert (inputValidator.validateInputDaysOfMonth("15", "12", "2004"));
		// Leap year
		assert (inputValidator.validateInputDaysOfMonth("29", "02", "2004"));
		assert (inputValidator.validateInputDaysOfMonth("28", "02", "2004"));
		// Edge cases
		assert (inputValidator.validateInputDaysOfMonth("30", "06", "2004"));
		assert (inputValidator.validateInputDaysOfMonth("31", "12", "2004"));
		assert (inputValidator.validateInputDaysOfMonth("01", "12", "2004"));
	}

	@Test
	public void daysOfMonthShouldBeNotBeOutOfValidRange() {
		assertFalse(inputValidator.validateInputDaysOfMonth("33", "12", "2004"));
		// Leap year
		assertFalse(inputValidator.validateInputDaysOfMonth("29", "02", "2003"));
		// Edge cases
		assertFalse(inputValidator.validateInputDaysOfMonth("31", "06", "2004"));
		assertFalse(inputValidator.validateInputDaysOfMonth("32", "12", "2004"));
		assertFalse(inputValidator.validateInputDaysOfMonth("00", "12", "2004"));
		assertFalse(inputValidator.validateInputDaysOfMonth("00", "01", "2004"));
	}

	@Test
	public void isTrueForValidDateInputs() {
		inputValidator.validateInput("01/02/2001", "01/02/2005");
		// Edge cases
		inputValidator.validateInput("01/01/2001", "31/12/2005");
		// Leap year
		inputValidator.validateInput("29/02/2004", "31/12/2005");
		inputValidator.validateInput("29/02/2004", "29/02/2008");
	}

	@Test
	public void isFalseForInValidDateInputs() {
		assertFalse(inputValidator.validateInput("39/02/2001", "49/02/2001"));
		// Empty check
		assertFalse(inputValidator.validateInput("", ""));
		// Null check
		assertFalse(inputValidator.validateInput(null, null));
		// Leap year
		assertFalse(inputValidator.validateInput("29/02/2001", "29/02/2004"));
		assertFalse(inputValidator.validateInput("29/02/2004", "29/02/2005"));
		//Edge cases
		assertFalse(inputValidator.validateInput("00/01/1901", "31/12/2999"));
		assertFalse(inputValidator.validateInput("01/01/1901", "32/12/2999"));
	}

}
