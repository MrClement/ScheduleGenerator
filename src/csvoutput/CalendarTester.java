package csvoutput;

import generator.CurrentDate;
import generator.Dates;
import generator.SchoolType;

import java.util.HashMap;

public class CalendarTester {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		CurrentDate startDate = new CurrentDate(Dates.SCHOOL_START_MONTH, Dates.SCHOOL_START_DAY,
				Dates.SCHOOL_START_YEAR);
		CurrentDate endDate = new CurrentDate(Dates.SCHOOL_END_MONTH, Dates.SCHOOL_END_DAY, Dates.SCHOOL_END_YEAR);
		CurrentDate earlyLimit = startDate;
		CurrentDate lateLimit = endDate;

		MultiDayCSVWriter writer = new MultiDayCSVWriter("excluded.txt", startDate, endDate, earlyLimit, lateLimit);

		SchoolType s = SchoolType.HIGH;
		// sets whether to put breaks, student life, and lunch on the calendar
		boolean includeBreaksAndLunch = true;
		// If this has entries it will only generate specific period on specific
		// days
		HashMap<Character, Integer> singleBox = new HashMap<Character, Integer>();
		// singleBox.put('G', 3);
		// singleBox.put('E', 7);
		// Specifies which periods will show up at all (if a period appear in
		// singleBox but not here, it will not be on the schedule
		int[] periodsToInclude = { 1, 2, 3, 4, 5, 6, 7 };
		// Specifies the names of all the periods
		String[] periodNames = { "Period 1", "Period 2", "Period 3", "Period 4", "Period 5", "Period 6", "Period 7" };

		boolean midSchoolElective = false;
		boolean sixth = false;
		int[] sixthPrefs = {};

		writer.generateCSVFile("/Users/aclement/Desktop/AllPeriods.csv", true);

	}
}
