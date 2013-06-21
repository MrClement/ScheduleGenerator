package generator;

import java.util.HashMap;

public class ScheduleGeneratorDriver {

	public ScheduleGeneratorDriver() {
		CurrentDate startDate = new CurrentDate(8, 21, 2013);
		CurrentDate endDate = new CurrentDate(5, 23, 2013);
		SchoolType school = SchoolType.HIGH;
		MultiDayWriter writer = new MultiDayWriter("excluded.txt", startDate, endDate, startDate, endDate);
		// sets whether to put breaks, student life, and lunch on the calendar
		boolean includeBreaksAndLunch = false;
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
		writer.generateICSFile("test.txt", periodsToInclude, periodNames, school, singleBox, includeBreaksAndLunch,
				false, false, new int[0]);

	}

	public ScheduleGeneratorDriver(ScheduleDataStorage data) {
		CurrentDate startDate = new CurrentDate(8, 21, 2013);
		CurrentDate endDate = new CurrentDate(5, 23, 2014);
		MultiDayWriter writer = new MultiDayWriter("excluded.txt", startDate, endDate, data.getStartDate(),
				data.getEndDate());
		writer.generateICSFile(data.getFilename(), data.getPeriodsToInclude(), data.getPeriodNames(), data.getSchool(),
				data.getSingleBox(), data.isIncludeBreaksAndLunch(), data.isMidSchoolElective(), data.isSixth(), data.getSixthPrefs());
	}

}
