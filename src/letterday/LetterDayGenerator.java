package letterday;

import generator.CurrentDate;
import generator.Dates;

public class LetterDayGenerator {

	public static void main(String[] args) {
		CurrentDate startDate = new CurrentDate(Dates.SCHOOL_START_MONTH, Dates.SCHOOL_START_DAY,
				Dates.SCHOOL_START_YEAR);
		CurrentDate endDate = new CurrentDate(Dates.SCHOOL_END_MONTH, Dates.SCHOOL_END_DAY, Dates.SCHOOL_END_YEAR);
		ICSLetterHelper ics = new ICSLetterHelper("Excluded.txt", startDate, endDate, startDate, endDate,
				"ICSLetterDays.txt");
		CSVLetterHelper csv = new CSVLetterHelper("Excluded.txt", startDate, endDate, startDate, endDate,
				"CSVLetterDays.csv");
	}

}
