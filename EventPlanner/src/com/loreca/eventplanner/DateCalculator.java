package com.loreca.eventplanner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.loreca.eventplanner.dto.DateDTO;

public interface DateCalculator {

	public static final Map<Integer, Integer> MONTHLY_DAY_COUNT = Collections
			.unmodifiableMap(new HashMap<Integer, Integer>() {
				{
					put(1, 31);
					put(2, 28); // Override for leap years
					put(3, 31);
					put(4, 30);
					put(5, 31);
					put(6, 30);
					put(7, 31);
					put(8, 31);
					put(9, 30);
					put(10, 31);
					put(11, 30);
					put(12, 31);
				}
			});

	public boolean isLeapYear(final int year);

	public int daysTillEndOfYear(final int day, final int month, final int year);
	
	public int daysFromStartOfYear(final int day, final int month, final int year);

	public int daysTillEndOfMonth(final int day, final int month, final int year);

	public int daysBetweenMonths(final int startMonth, final int endMonth, final int year);

	public int daysBetweenYears(final int startYear, final int endYear);

	public int daysTillEndOfYear(final DateDTO dateDto);

	public int daysTillEndOfMonth(final DateDTO dateDto);
	
	public int daysFromStartOfYear(final DateDTO dateDto);

	/*
	 * This method will follow a process of elimination from most general case
	 * to most specific using 2 DateDTO objects containing start and end dates
	 * 1) If date objects match return 0 
	 * 2) If years and months match, return just the date difference excluding partial days 
	 * 3) If only years match, calculate the number of days between months and return excluding partial days 
	 * 4) If years are different, calculate the number of days between years and return excluding partial days
	 */
	public int daysBetween(final DateDTO startDate, final DateDTO endDate);
}
