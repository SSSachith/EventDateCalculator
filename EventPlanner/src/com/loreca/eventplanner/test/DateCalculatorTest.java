package com.loreca.eventplanner.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.loreca.eventplanner.DateCalculator;
import com.loreca.eventplanner.DateCalculatorImpl;
import com.loreca.eventplanner.dto.DateDTO;

public class DateCalculatorTest {

	static DateCalculator dateCalculator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dateCalculator = new DateCalculatorImpl();
	}

	@Test
	public void isTrueForAllValidLeapYears() {
		DateCalculator dateCalculator = new DateCalculatorImpl();
		int[] years = new int[] { 1904, 1908, 1912, 1916, 1920, 1924, 1928, 1932, 1936, 1940, 1944, 1948, 1952, 1956,
				1960, 1964, 1968, 1972, 1976, 1980, 1984, 1988, 1992, 1996, 2000, 2004, 2008, 2012, 2016, 2020, 2024,
				2028, 2032, 2036, 2040, 2044, 2048, 2052, 2056, 2060, 2064, 2068, 2072, 2076, 2080, 2084, 2088, 2092,
				2096, 2104, 2108, 2112, 2116, 2120, 2124, 2128, 2132, 2136, 2140, 2144, 2148, 2152, 2156, 2160, 2164,
				2168, 2172, 2176, 2180, 2184, 2188, 2192, 2196, 2204, 2208, 2212, 2216, 2220, 2224, 2228, 2232, 2236,
				2240, 2244, 2248, 2252, 2256, 2260, 2264, 2268, 2272, 2276, 2280, 2284, 2288, 2292, 2296, 2304, 2308,
				2312, 2316, 2320, 2324, 2328, 2332, 2336, 2340, 2344, 2348, 2352, 2356, 2360, 2364, 2368, 2372, 2376,
				2380, 2384, 2388, 2392, 2396, 2400, 2404, 2408, 2412, 2416, 2420, 2424, 2428, 2432, 2436, 2440, 2444,
				2448, 2452, 2456, 2460, 2464, 2468, 2472, 2476, 2480, 2484, 2488, 2492, 2496, 2504, 2508, 2512, 2516,
				2520, 2524, 2528, 2532, 2536, 2540, 2544, 2548, 2552, 2556, 2560, 2564, 2568, 2572, 2576, 2580, 2584,
				2588, 2592, 2596, 2604, 2608, 2612, 2616, 2620, 2624, 2628, 2632, 2636, 2640, 2644, 2648, 2652, 2656,
				2660, 2664, 2668, 2672, 2676, 2680, 2684, 2688, 2692, 2696, 2704, 2708, 2712, 2716, 2720, 2724, 2728,
				2732, 2736, 2740, 2744, 2748, 2752, 2756, 2760, 2764, 2768, 2772, 2776, 2780, 2784, 2788, 2792, 2796,
				2800, 2804, 2808, 2812, 2816, 2820, 2824, 2828, 2832, 2836, 2840, 2844, 2848, 2852, 2856, 2860, 2864,
				2868, 2872, 2876, 2880, 2884, 2888, 2892, 2896, 2904, 2908, 2912, 2916, 2920, 2924, 2928, 2932, 2936,
				2940, 2944, 2948, 2952, 2956, 2960, 2964, 2968, 2972, 2976, 2980, 2984, 2988, 2992, 2996 };
		for (int year : years) {
			assert (dateCalculator.isLeapYear(year));
		}
	}

	@Test
	public void isFalseForInvalidLeapYears() {
		int[] years = new int[] { 1983, 2005, 2007, 1999 };
		for (int year : years) {
			assertFalse(dateCalculator.isLeapYear(year));
		}
	}

	@Test
	public void givesTheCorrectNumberOfDaysTillEndOfYear() {
		assertEquals(dateCalculator.daysTillEndOfYear(1, 1, 1985), 365);
		// Leap year
		assertEquals(dateCalculator.daysTillEndOfYear(1, 1, 2004), 366);
		// Using DTO
		assertEquals(dateCalculator.daysTillEndOfYear(new DateDTO(1, 1, 2004)), 366);
	}

	@Test
	public void givesTheCorrectNumberOfDaysTillEndOfMonth() {
		assertEquals(dateCalculator.daysTillEndOfMonth(1, 1, 1985), 31);
		// Leap year
		assertEquals(dateCalculator.daysTillEndOfMonth(1, 2, 2004), 29);
		// Using DTO
		assertEquals(dateCalculator.daysTillEndOfMonth(new DateDTO(1, 2, 2004)), 29);
	}

	@Test
	public void giveTheCorrectNumberOfDaysBetweenMonths() {
		assertEquals(dateCalculator.daysBetweenMonths(2, 5, 1985), 120);
		// For immediate months
		assertEquals(dateCalculator.daysBetweenMonths(2, 3, 1985), 59);
		// Leap years
		assertEquals(dateCalculator.daysBetweenMonths(2, 3, 2004), 60);
	}

	@Test
	public void givesTheCorrectNumberOfDaysBetweenYears() {
		assertEquals(dateCalculator.daysBetweenYears(1901, 1909), 3287);
		// Edge case
		assertEquals(dateCalculator.daysBetweenYears(1901, 2999), 401402);
	}

	@Test
	public void equalDatesShouldReturnZero() {
		assertEquals(dateCalculator.daysBetween(new DateDTO(1, 1, 1999), new DateDTO(1, 1, 1999)), 0);
		// Edge cases
		assertEquals(dateCalculator.daysBetween(new DateDTO(1, 1, 1901), new DateDTO(1, 1, 1901)), 0);
		assertEquals(dateCalculator.daysBetween(new DateDTO(31, 12, 2999), new DateDTO(31, 12, 2999)), 0);
	}

	@Test
	public void partialDaysShouldDefaultToZero() {
		assertEquals(dateCalculator.daysBetween(new DateDTO(1, 1, 1999), new DateDTO(2, 1, 1999)), 0);
		assertEquals(dateCalculator.daysBetween(new DateDTO(12, 12, 1999), new DateDTO(13, 12, 1999)), 0);
	}

	@Test
	public void givesCorrectNumberOfDaysWhenMonthsAndYearsAreEqual() {
		assertEquals(dateCalculator.daysBetween(new DateDTO(1, 1, 1999), new DateDTO(10, 1, 1999)), 8);
	}

	@Test
	public void givesCorrectNumberOfDaysWhenOnlyYearsAreEqual() {
		assertEquals(dateCalculator.daysBetween(new DateDTO(1, 1, 1999), new DateDTO(10, 2, 1999)), 39);
	}

	@Test
	public void givesCorrectNumberOfDaysFromStartOfTheYear() {
		assertEquals(dateCalculator.daysFromStartOfYear(new DateDTO(1, 1, 1999)), 1);
		assertEquals(dateCalculator.daysFromStartOfYear(new DateDTO(2, 1, 1999)), 2);
		// Edge cases
		assertEquals(dateCalculator.daysFromStartOfYear(new DateDTO(31, 12, 2001)), 365);
		assertEquals(dateCalculator.daysFromStartOfYear(new DateDTO(31, 12, 2999)), 365);
		assertEquals(dateCalculator.daysFromStartOfYear(new DateDTO(1, 1, 2999)), 1);
		// Leap year
		assertEquals(dateCalculator.daysFromStartOfYear(new DateDTO(29, 2, 2004)), 60);
		assertEquals(dateCalculator.daysFromStartOfYear(new DateDTO(31, 12, 2004)), 366);
	}

	@Test
	public void givesCorrectNumberOfDaysWhenYearsAreDifferent() {
		assertEquals(dateCalculator.daysBetween(new DateDTO(1, 2, 1999), new DateDTO(10, 1, 2099)), 36502);
		assertEquals(dateCalculator.daysBetween(new DateDTO(12, 12, 2004), new DateDTO(12, 12, 2005)), 364);
	}

}
