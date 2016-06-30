package letterday;

import generator.CurrentDate;
import generator.Dates;

public class LetterDayGenerator {

	public static void main(String[] args) {
		CurrentDate startDate = new CurrentDate(Dates.SCHOOL_START_MONTH, Dates.SCHOOL_START_DAY,
				Dates.SCHOOL_START_YEAR);
		CurrentDate endDate = new CurrentDate(Dates.SCHOOL_END_MONTH, Dates.SCHOOL_END_DAY, Dates.SCHOOL_END_YEAR);
		//ICSLetterHelper ics = new ICSLetterHelper("excluded.txt", startDate, endDate, startDate, endDate,
		//		"ICSLetterDays.txt");
		CSVLetterHelper csv = new CSVLetterHelper("excluded.txt", startDate, endDate, startDate, endDate,
				"CSVLetterDaysBF.csv");
	}

}
