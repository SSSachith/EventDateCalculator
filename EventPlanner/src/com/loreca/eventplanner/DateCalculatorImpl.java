package com.loreca.eventplanner;

import com.loreca.eventplanner.dto.DateDTO;

public class DateCalculatorImpl implements DateCalculator {
	
	@Override
	public boolean isLeapYear(final int year) {
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}

	@Override
	public int daysTillEndOfYear(final int day, final int month, final int year) {
		int count = daysTillEndOfMonth(day, month, year);
		int currentMonth = month;
		currentMonth++;

		while (currentMonth <= 12) {
			count += daysTillEndOfMonth(1, currentMonth, year);
			currentMonth++;
		}
		return count;
	}

	@Override
	public int daysTillEndOfMonth(final int day, final int month, final int year) {
		// One added to include the current day
		int count = MONTHLY_DAY_COUNT.get(month) - day + 1;
			
		if (month == 2 && isLeapYear(year)) {
			count++;
		}
		return count;
	}

	@Override
	public int daysBetweenMonths(final int startMonth, final int endMonth, final int year) {
		int count = 0;
		int currentMonth = startMonth;

		while (currentMonth <= endMonth) {
			count += MONTHLY_DAY_COUNT.get(currentMonth);
			if (currentMonth == 2 && isLeapYear(year)) {
				count++;
			}
			currentMonth++;
		}
		return count;
	}

	@Override
	public int daysBetweenYears(int startYear, int endYear) {
		int count = 0;
		int currentYear = startYear;

		while (currentYear <= endYear) {
			count += 365;
			if (isLeapYear(currentYear)) {
				count++;
			}
			currentYear++;
		}
		return count;
	}

	@Override
	public int daysTillEndOfYear(DateDTO dateDto) {
		return daysTillEndOfYear(dateDto.getDay(), dateDto.getMonth(), dateDto.getYear());
	}

	@Override
	public int daysTillEndOfMonth(DateDTO dateDto) {
		return daysTillEndOfMonth(dateDto.getDay(), dateDto.getMonth(), dateDto.getYear());
	}

	@Override
	public int daysBetween(DateDTO startDate, DateDTO endDate) {
		if (startDate.equals(endDate)) {
			return 0;
		} else if (startDate.getMonth() == endDate.getMonth() && startDate.getYear() == endDate.getYear()) {
			int count = endDate.getDay() - startDate.getDay() - 1;
			return count <= 0 ? 0 : count;
		} else if (startDate.getYear() == endDate.getYear()) {
			int count = daysTillEndOfMonth(startDate) + endDate.getDay() - 2;
			// If there are whole months between given months,
			// get all the days and add to count
			count = endDate.getMonth() - startDate.getMonth() > 1
					? count += daysBetweenMonths(startDate.getMonth() + 1, endDate.getMonth() - 1, endDate.getYear())
					: count;
			return count;
		} else {
			int count = daysTillEndOfYear(startDate) + endDate.getDay() - 2;
			count = endDate.getYear() - startDate.getYear() > 1
					? count += daysBetweenYears(startDate.getYear() + 1, endDate.getYear() - 1) : count;
			return count;
		}
	}
}
