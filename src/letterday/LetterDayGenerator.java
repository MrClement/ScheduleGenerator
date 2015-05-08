package letterday;

import generator.CurrentDate;

public class LetterDayGenerator {

	public static void main(String[] args) {
		CurrentDate startDate = new CurrentDate(8, 26, 2015);
		CurrentDate endDate = new CurrentDate(5, 27, 2016);
		ICSLetterHelper ics = new ICSLetterHelper("Excluded.txt", startDate, endDate, startDate, endDate,
				"ICSLetterDays.txt");
		CSVLetterHelper csv = new CSVLetterHelper("Excluded.txt", startDate, endDate, startDate, endDate,
				"CSVLetterDays.csv");
	}

}
