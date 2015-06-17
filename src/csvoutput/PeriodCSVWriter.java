package csvoutput;

import generator.CurrentDate;
import generator.Dates;
import generator.Day;
import generator.DayBuilder;
import generator.DayBuilder6;
import generator.DayBuilder78;
import generator.Excluder;
import generator.Period;
import generator.SchoolType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TimeZone;

public class PeriodCSVWriter {
	private HashMap<Character, Day> normalDays;
	private HashMap<Character, Day> wednesDays;
	private HashMap<Character, Day> normalDaysDST;
	private HashMap<Character, Day> wednesDaysDST;
	private PriorityQueue<CurrentDate> excludedElectives;
	private PriorityQueue<CurrentDate> excludedSevenEight;
	private PriorityQueue<CurrentDate> excludedSixth;
	private BufferedWriter out;
	private String filename;
	private int startYear = Dates.START_YEAR;
	private CurrentDate schoolStartDate = new CurrentDate(Dates.SCHOOL_START_MONTH, Dates.SCHOOL_START_DAY,
			Dates.SCHOOL_START_YEAR);
	private CurrentDate dstStartDate = new CurrentDate(Dates.DST_START_MONTH, Dates.DST_START_DAY, Dates.DST_START_YEAR);
	private CurrentDate dstEndDate = new CurrentDate(Dates.DST_END_MONTH, Dates.DST_END_DAY, Dates.DST_END_YEAR);
	private CurrentDate dayAfterDSTStartDate = new CurrentDate(Dates.DST_START_MONTH, Dates.DST_START_DAY + 1,
			Dates.DST_START_YEAR);
	private SchoolType school;

	public PeriodCSVWriter(String filename, SchoolType s) throws IOException {
		this.school = s;
		DayBuilder db = null;
		switch (s) {
			case SIXTH:
				db = new DayBuilder6();
				break;
			case SEVENEIGHT:
				db = new DayBuilder78();
				break;
			case HIGH:
				db = new DayBuilder();
				break;
			default:
				break;
		}

		normalDays = db.makeNormalDays(adjustTime(800, schoolStartDate, 1));
		wednesDays = db.makeWednesdays(adjustTime(900, schoolStartDate, 1));
		normalDaysDST = db.makeNormalDays(adjustTime(800, dayAfterDSTStartDate, 1));
		wednesDaysDST = db.makeWednesdays(adjustTime(900, dayAfterDSTStartDate, 1));

		Excluder e = new Excluder();
		excludedElectives = e.makeExclusionQueue("ExcludedElectives.txt");
		excludedSevenEight = e.makeExclusionQueue("ExcludedSevenEight.txt");
		excludedSixth = e.makeExclusionQueue("ExcludedSixth.txt");

		this.filename = filename;
	}

	public int adjustTime(int time, CurrentDate today, int direction) {
		int currentTime = time;
		if (today.isBefore(dstStartDate) || today.isAfterOrEqual(dstEndDate)) {
			currentTime += 600 * direction;
		} else {
			currentTime += 700 * direction;
		}
		return currentTime;

	}

	public boolean isWednesday(CurrentDate today) {
		Calendar wed = Calendar.getInstance();
		wed.clear();
		wed.set(startYear, 0, 1, 8, 8, 8);
		while (wed.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
			wed.add(Calendar.DATE, 1);
		}
		Calendar temp = Calendar.getInstance();
		temp.clear();
		temp.set(today.getYear(), today.getMonth() - 1, today.getDay(), 8, 8, 8);
		while (wed.compareTo(temp) < 0) {
			wed.add(Calendar.DAY_OF_WEEK, 7);
			if (wed.compareTo(temp) == 0) {
				return true;
			}
		}
		return false;

	}

	public void writeHeader() {
		File old = new File(filename);
		old.delete();
		try {
			out = new BufferedWriter(new FileWriter(filename, true));

			out.write("Class Title, Start Time, End Time");
			out.newLine();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String periodName(int periodNumber, String[] periodNames, CurrentDate today, boolean sixth, int[] sixthPrefs) {
		if (sixth) {
			return periodNameSixth(periodNumber, sixthPrefs);
		} else {
			return periodNameNonSixth(periodNumber, periodNames, today);
		}
	}

	private String periodNameSixth(int periodNumber, int[] sixthPrefs) {
		String s = "";
		switch (periodNumber) {
			case Period.HOMEROOM:
				s = "Homeroom";
				break;
			case Period.GEOBASEBALL:
				s = "Geobaseball";
				break;
			case Period.ELECTIVES:
				s = "Electives";
				break;
			case Period.LUNCH:
				s = "Lunch";
				break;
			case Period.ASSEMBLY:
				s = "Assembly";
				break;
			case Period.BREAK:
				s = "Break";
				break;
			case Period.CLUBS:
				s = "Clubs";
				break;
			case Period.LANG12:
				switch (sixthPrefs[0]) {
					case 1:
					case 2:
						s = "Language Arts";
						break;
					case 3:
					case 4:
						s = "Math";
						break;

					default:
						break;
				}
				break;
			case Period.SS12:
				switch (sixthPrefs[0]) {
					case 1:
					case 2:
						s = "Social Studies";
						break;
					case 3:
					case 4:
						s = "Science";
						break;

					default:
						break;
				}
				break;
			case Period.LANG34:
				switch (sixthPrefs[0]) {
					case 1:
					case 2:
						s = "Math";
						break;
					case 3:
					case 4:
						s = "Language Arts";
						break;

					default:
						break;
				}
				break;
			case Period.SS34:
				switch (sixthPrefs[0]) {
					case 1:
					case 2:
						s = "Science";
						break;
					case 3:
					case 4:
						s = "Social Studies";
						break;

					default:
						break;
				}
				break;
			case Period.ROT1:
				switch (sixthPrefs[3]) {
					case 1:
						s = "PE";
						break;
					case 2:
						s = "Latin";
						break;
					case 3:
						s = "Art";
						break;
					case 4:
						s = "Writing";
						break;
					default:
						break;
				}
				break;
			case Period.ROT2:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Art";
						break;
					case 2:
						s = "PE";
						break;
					case 3:
						s = "Latin";
						break;
					case 4:
						s = "Study Hall";
						break;
					default:
						break;
				}
				break;
			case Period.ROT3:
				switch (sixthPrefs[3]) {
					case 1:
						s = "PE";
						break;
					case 2:
						s = "PE";
						break;
					case 3:
						s = "Writing";
						break;
					case 4:
						s = "Art";
						break;
					default:
						break;
				}
				break;
			case Period.ROT4:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Writing";
						break;
					case 2:
						s = "Art";
						break;
					case 3:
						s = "PE";
						break;
					case 4:
						s = "Latin";
						break;
					default:
						break;
				}
				break;
			case Period.ROT5:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Art";
						break;
					case 2:
						s = "Writing";
						break;
					case 3:
						s = "PE";
						break;
					case 4:
						s = "PE";
						break;
					default:
						break;
				}
				break;
			case Period.ROT6:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Latin";
						break;
					case 2:
						s = "PE";
						break;
					case 3:
						s = "Art";
						break;
					case 4:
						s = "Writing";
						break;
					default:
						break;
				}
				break;
			case Period.ROT7:
				switch (sixthPrefs[3]) {
					case 1:
						s = "PE";
						break;
					case 2:
						s = "Latin";
						break;
					case 3:
						s = "Study Hall";
						break;
					case 4:
						s = "Art";
						break;
					default:
						break;
				}
				break;
			case Period.ROT8:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Writing";
						break;
					case 2:
						s = "Art";
						break;
					case 3:
						s = "Latin";
						break;
					case 4:
						s = "Study Hall";
						break;
					default:
						break;
				}
				break;
			case Period.ROT9:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Study Hall";
						break;
					case 2:
						s = "Writing";
						break;
					case 3:
						s = "PE";
						break;
					case 4:
						s = "Art";
						break;
					default:
						break;
				}
				break;
			case Period.ROT10:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Latin";
						break;
					case 2:
						s = "Study Hall";
						break;
					case 3:
						s = "Art";
						break;
					case 4:
						s = "PE";
						break;
					default:
						break;
				}
				break;
			case Period.ROT11:
				switch (sixthPrefs[3]) {
					case 1:
						s = "PE";
						break;
					case 2:
						s = "Latin";
						break;
					case 3:
						s = "Art";
						break;
					case 4:
						s = "Writing";
						break;
					default:
						break;
				}
				break;
			case Period.ROT12:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Art";
						break;
					case 2:
						s = "PE";
						break;
					case 3:
						s = "Writing";
						break;
					case 4:
						s = "Latin";
						break;
					default:
						break;
				}
				break;
			case Period.ROT13:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Writing";
						break;
					case 2:
						s = "Art";
						break;
					case 3:
						s = "Latin";
						break;
					case 4:
						s = "PE";
						break;
					default:
						break;
				}
				break;
			case Period.ROT14:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Latin";
						break;
					case 2:
						s = "WTRG";
						break;
					case 3:
						s = "Study Hall";
						break;
					case 4:
						s = "Art";
						break;
					default:
						break;
				}
				break;
			case Period.ROT15:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Art";
						break;
					case 2:
						s = "Study Hall";
						break;
					case 3:
						s = "Writing";
						break;
					case 4:
						s = "PE";
						break;
					default:
						break;
				}
				break;
			case Period.ROT16:
				switch (sixthPrefs[3]) {
					case 1:
						s = "Study Hall";
						break;
					case 2:
						s = "Art";
						break;
					case 3:
						s = "PE";
						break;
					case 4:
						s = "Latin";
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
		return s;
	}

	private String periodNameNonSixth(int periodNumber, String[] periodNames, CurrentDate today) {
		String s = "";
		switch (periodNumber) {
			case Period.BREAK:
				s = "Break";
				break;
			case Period.ASSEMBLY:
				s = "Assembly";
				break;
			case Period.CLASSMEETING:
				s = "Class Meeting";
				break;
			case Period.ADVISORY:
				s = "Advisory";
				break;
			case Period.CLUBS:
				s = "Clubs";
				break;
			case Period.LUNCH:
				s = "Lunch";
				break;
			case Period.SPORTS:
				s = "Sports";
				break;
			case Period.STUDYHALL:
				s = "Study Hall";
				break;
			case Period.ELECTIVES:
				s = "Electives";
				break;
			case Period.COMMUNITYTIME:
				s = communityTime(today);
				break;
			default:
				s = periodNames[periodNumber - 1];
				break;
		}
		return s;
	}

	public void writeDayToFile(char dayType,
			CurrentDate today,
			int[] periodsToInclude,
			String[] periodNames,
			HashMap<Character, Integer> singleBox,
			int[] sixthPrefs,
			boolean includeBreaksAndLunch,
			boolean midSchoolElective,
			boolean sixth) throws IOException {
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HashMap<Character, Day> currentDayMap;
		if (today.isBefore(dstStartDate) || today.isAfterOrEqual(dstEndDate)) {
			currentDayMap = isWednesday(today) ? wednesDays : normalDays;
		} else {
			currentDayMap = isWednesday(today) ? wednesDaysDST : normalDaysDST;
		}
		Day dayToPrint = currentDayMap.get(dayType);
		for (Period p : dayToPrint.getD()) {
			if (periodShouldBeIncluded(p.getNumber(), periodsToInclude, includeBreaksAndLunch, midSchoolElective, today)) {
				if (singleBox.size() < 1 || (singleBox.containsKey(dayType) && p.getNumber() == singleBox.get(dayType))) {
					Period periodToPrint = p;
					GregorianCalendar temp;
					temp = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
					temp.set(GregorianCalendar.SECOND, 0);
					temp.set(GregorianCalendar.YEAR, today.getYear());
					temp.set(GregorianCalendar.MONTH, today.getMonth() - 1);
					temp.set(GregorianCalendar.DAY_OF_MONTH, today.getDay());
					temp.set(GregorianCalendar.HOUR_OF_DAY, periodToPrint.getStartTime() / 100);
					temp.set(GregorianCalendar.MINUTE, periodToPrint.getStartTime() % 100);
					GregorianCalendar denverTime = new GregorianCalendar(TimeZone.getTimeZone("America/Denver"));
					denverTime.setTimeInMillis(temp.getTimeInMillis());
					String startTime = denverTime.toZonedDateTime().format(
							DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a z"));
					temp.setTimeZone(TimeZone.getTimeZone("GMT"));
					temp.set(GregorianCalendar.HOUR_OF_DAY, periodToPrint.getEndTime() / 100);
					temp.set(GregorianCalendar.MINUTE, periodToPrint.getEndTime() % 100);
					denverTime.setTimeInMillis(temp.getTimeInMillis());
					String endTime = denverTime.toZonedDateTime().format(
							DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a z"));
					String title = periodName(periodToPrint.getNumber(), periodNames, today, sixth, sixthPrefs);
					String line = title + ", " + startTime + ", " + endTime;
					out.write(line);
					out.newLine();
				}
			}
		}
		out.close();

	}

	private boolean periodShouldBeIncluded(int periodNumber,
			int[] periodsToInclude,
			boolean includeBreaksAndLunch,
			boolean midSchoolElective,
			CurrentDate today) {
		boolean found = false;
		boolean electiveInclusion = true;
		for (int i = 0; i < periodsToInclude.length; i++) {
			if (found = periodsToInclude[i] == periodNumber)
				break;
		}
		for (CurrentDate c : excludedElectives) {
			if (c.getMonth() == today.getMonth() && c.getYear() == today.getYear() && c.getDay() == today.getDay()) {
				electiveInclusion = false;
			}
		}
		switch (school) {
			case SEVENEIGHT:
				for (CurrentDate c : excludedSevenEight) {
					if (c.getMonth() == today.getMonth() && c.getYear() == today.getYear()
							&& c.getDay() == today.getDay()) {
						return false;
					}
				}
				break;
			case SIXTH:
				for (CurrentDate c : excludedSixth) {
					if (c.getMonth() == today.getMonth() && c.getYear() == today.getYear()
							&& c.getDay() == today.getDay()) {
						return false;
					}
				}
				break;
			default:
				break;
		}
		return (((periodNumber < 0 && periodNumber > -15) && includeBreaksAndLunch) || (periodNumber == Period.ELECTIVES
				&& midSchoolElective && electiveInclusion))
				|| found;
	}

	private String communityTime(CurrentDate today) {
		switch (today.getDayOfTheWeek()) {
			case 2:
				return "Advisory";
			case 3:
				return "Assembly";
			case 4:
				return "Class Meeting";
			case 5:
				return "Clubs";
			case 6:
				return "Assembly";
			default:
				return "";
		}
	}

}
