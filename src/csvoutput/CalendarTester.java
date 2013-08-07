package csvoutput;

import generator.CurrentDate;
import generator.MultiDayWriter;
import generator.ScheduleGeneratorDriver;
import generator.SchoolType;

import java.io.IOException;
import java.util.HashMap;

public class CalendarTester {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		CurrentDate startDate = new CurrentDate(8, 21, 2013);
		CurrentDate endDate = new CurrentDate(5, 23, 2014);
		boolean schoolbool = true;
		MultiDayCSVWriter writer = new MultiDayCSVWriter("excluded.txt", startDate, endDate, startDate, endDate);
		// sets whether to put breaks, student life, and lunch on the calendar
		boolean includeBreaksAndLunch = true;
		// If this has entries it will only generate specific period on specific
		// days
		HashMap<Character, Integer> singleBox = new HashMap<Character, Integer>();
		// singleBox.put('G', 3);
		// singleBox.put('E', 7);
		// Specifies which periods will show up at all (if a period appears in
		// singleBox but not here, it will not be on the schedule
		int[] periodsToInclude = { 2, 5, 6 };
		// Specifies the names of all the periods
		String[] periodNames = { "Period 1", "Period 2", "Technology Department Meeting", "Period 4", "Period 5",
				"Period 6", "Middle School Robotics" };
		// specifies the name of the output file
		writer.generateCSVFile("test.txt", schoolbool);

	}
}
