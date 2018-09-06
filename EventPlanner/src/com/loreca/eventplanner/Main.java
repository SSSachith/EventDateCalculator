package com.loreca.eventplanner;

import java.util.Scanner;

import com.loreca.eventplanner.dto.DateDTO;

public class Main {

	/*
	 * Assumptions:
	 * - Year has a total of 365 days and a leap year has 366 days 
	 * - First date is lower than second date 
	 * - Any input not between 01/01/1901 and 31/12/2999 are invalid 
	 * - Any input not in DD/MM/YYYY format are invalid 
	 * - Input month(MM) should be between 01-12, day(DD) should be 01-31 
	 * - For leap years February(02) has 29 days, for normal years that input will be invalid
	 * 
	 */
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String startDate = null;
		String endDate = null;
		InputValidator inputValidator = new InputValidatorImpl();
		boolean hasValidInput = false;

		while (!hasValidInput) {
			System.out.print("Enter event start and end date separated by space: (DD/MM/YYYY DD/MM/YYYY): ");
			startDate = scanner.next();
			endDate = scanner.next();
			hasValidInput = inputValidator.validateInput(startDate, endDate);
		}
		scanner.close();

		DateDTO startDateDTO = new DateDTO(startDate);
		DateDTO endDateDTO = new DateDTO(endDate);
		DateCalculator dateCalculator = new DateCalculatorImpl();

		System.out.println("Days between " + startDateDTO + " and " + endDateDTO + " = "
				+ dateCalculator.daysBetween(startDateDTO, endDateDTO));
	}

}
