package com.loreca.eventplanner.dto;

/* This class encapsulates a date and can be used to create data transfer objects for dates
 * Setters not included to make it an immutable object
 * */
public class DateDTO {

	private final int day;
	private final int month;
	private final int year;

	public DateDTO(String inputDate) {
		String[] date = inputDate.split("/");
		this.day = Integer.parseInt(date[0]);
		this.month = Integer.parseInt(date[1]);
		this.year = Integer.parseInt(date[2]);
	}

	public DateDTO(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof DateDTO)) {
			return false;
		}
		DateDTO c = (DateDTO) o;
		return c.day == this.day && c.month == this.month && c.year == this.year;
	}

	@Override
	public String toString() {
		return this.day + "/" + this.month + "/" + this.year;
	}

}
