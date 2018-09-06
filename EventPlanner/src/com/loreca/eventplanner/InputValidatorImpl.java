package com.loreca.eventplanner;

public class InputValidatorImpl implements InputValidator {

	@Override
	public boolean validateInput(final String input1, final String input2) {

		boolean isValid = false;
		String[] startDate = null;
		String[] endDate = null;

		isValid = !(input1 == null || input2 == null);
		if (isValid) {
			isValid = validateInputPattern(input1) && validateInputPattern(input2);
		}
		if (isValid) {
			startDate = input1.split("/");
			endDate = input2.split("/");
			isValid = validateInputYearRange(startDate[2]) && validateInputYearRange(endDate[2]);
		}
		if (isValid) {
			isValid = validateInputMonthRange(startDate[1]) && validateInputMonthRange(endDate[1]);
		}
		if (isValid) {
			isValid = validateInputDaysOfMonth(startDate[0], startDate[1], startDate[2])
					&& validateInputDaysOfMonth(endDate[0], endDate[1], endDate[2]);
		}
		return isValid;
	}

	@Override
	public boolean validateInputPattern(final String input) {
		return input.matches("\\d{2}/\\d{2}/\\d{4}");
	}

	@Override
	public boolean validateInputYearRange(final String input) {
		int year = Integer.parseInt(input);
		return year <= 2999 && year >= 1901;
	}

	@Override
	public boolean validateInputMonthRange(final String input) {
		int month = Integer.parseInt(input);
		return month <= 12 && month >= 1;
	}

	@Override
	public boolean validateInputDaysOfMonth(final String inputDay, final String inputMonth, final String inputYear) {
		int month = Integer.parseInt(inputMonth);
		int day = Integer.parseInt(inputDay);
		int year = Integer.parseInt(inputYear);

		DateCalculator dateCalculator = new DateCalculatorImpl();

		int validMaxDays = (month == 2 && dateCalculator.isLeapYear(year)) ? 29
				: DateCalculator.MONTHLY_DAY_COUNT.get(month);

		return day <= validMaxDays && day >= 1;
	}

}
